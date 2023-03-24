<template>
  <v-dialog v-model="dialog" width="800" persistent>
    <template v-slot:activator="{ props }">
      <v-btn class="ma-2" color="primary" v-bind="props"> New request </v-btn>
    </template>
    <form @submit.prevent="submit">
      <v-card elevation="12">
        <v-card-title>
          <span class="text-h5"> New Deposit Request </span>
        </v-card-title>
        <v-card-text>
          <v-container>
            <v-alert
              color="primary"
              variant="outlined"
              class="mb-8"
              icon="mdi-cash-plus"
            >
              <template v-slot:title> Instructions </template>
              <br />
              To topup your account, please deposit into the following bank
              account, and let us know about the details of the deposits. Your
              account balance will be credited within 1 business day after we
              have verified the transfer details.
              <br /><br />
              <strong>Bank:</strong> Canadian Imperial Bank of Commerce (CIBC)
              <br />
              <strong>Bank Account Number:</strong> 001-00001-1234567
            </v-alert>
            <FormAlert :message="error" />
            <v-row>
              <v-col cols="12">
                <v-text-field
                  label="Amount"
                  v-model="form.amount"
                  :error-messages="getMessage('amount')"
                  prefix="C$"
                ></v-text-field>
              </v-col>
            </v-row>
            <v-row>
              <v-col cols="12">
                <v-text-field
                  label="Bank reference"
                  v-model="form.bankReference"
                  :error-messages="getMessage('bankReference')"
                ></v-text-field>
              </v-col>
            </v-row>
            <v-row>
              <v-col cols="12">
                <v-input :error-messages="getMessage('depositDate')">
                  <Datepicker
                    v-model="form.depositDate"
                    auto-apply
                    utc="preserve"
                    format="yyyy-MM-dd"
                    teleport-center
                    :enable-time-picker="false"
                    placeholder="Date of deposit"
                  />
                </v-input>
              </v-col>
            </v-row>
          </v-container>
        </v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>

          <v-btn color="secondary" variant="text" @click="cancel">
            Cancel
          </v-btn>
          <v-btn color="primary" variant="elevated" type="submit">
            Submit
          </v-btn>
        </v-card-actions>
      </v-card>
    </form>
  </v-dialog>
</template>

<script>
import accountService from "@/services/account.service";
import Datepicker from "@vuepic/vue-datepicker";
import "@vuepic/vue-datepicker/dist/main.css";
import FormAlert from "./FormAlert.vue";
import { useVuelidate } from "@vuelidate/core";
import { required, minValue } from "@vuelidate/validators";

export default {
  setup() {
    return { vuelidate: useVuelidate() };
  },
  data() {
    return {
      form: {
        depositDate: "",
        amount: "",
        bankReference: "",
      },
      dialog: false,
      error: null,
    };
  },
  validations: {
    form: {
      depositDate: { required },
      amount: { minValue: minValue(0.1), required },
      bankReference: { required },
    },
  },
  components: { Datepicker, FormAlert },
  name: "DepositRequestForm",
  methods: {
    submit() {
      this.vuelidate
        .$validate()
        .then((isFormOK) => {
          if (isFormOK) {
            return accountService.submitDepositRequest(this.form);
          } else {
            return Promise.reject();
          }
        })
        .then(() => {
          this.dialog = false;
          this.$emit("deposit-submitted");
          this.$store.dispatch("account/balanceChanged");
          this.$store.dispatch(
            "alert/pushInfo",
            "Request submitted successfully"
          );
        })
        .catch((error) => {
          if (error) {
            if (error.response?.status == 400) {
              this.error = error.response.data;
            } else {
              this.$store.dispatch(
                "alert/push",
                "Sorry, we encountered error while trying to submit your reqquest. Please try again later"
              );
            }
          }
        });
    },
    cancel() {
      this.dialog = false;
    },
    getMessage(field) {
      return this.vuelidate.form[field].$errors.map((e) => e.$message);
    },
  },
};
</script>

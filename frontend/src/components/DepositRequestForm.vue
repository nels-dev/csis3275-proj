<template>
  <v-dialog v-model="dialog" width="80vw">
    <template v-slot:activator="{ props }">
      <v-btn class="ma-2" color="primary" v-bind="props"> New request </v-btn>
    </template>

    <v-card height="70vh">
      <v-card-title>New Deposit Request</v-card-title>
      <v-container>
        <form @submit.prevent="submit">
          <FormAlert :message="error" />

          <v-text-field
            label="Amount"
            v-model="form.amount"
            :error-messages="
              vuelidate.form.amount.$errors.map((e) => e.$message)
            "
            prefix="C$"
          ></v-text-field>
          <v-text-field
            label="Bank reference"
            v-model="form.bankReference"
            :error-messages="
              vuelidate.form.bankReference.$errors.map((e) => e.$message)
            "
          ></v-text-field>
          <v-input
            :error-messages="
              vuelidate.form.depositDate.$errors.map((e) => e.$message)
            "
          >
            <Datepicker
              v-model="form.depositDate"
              auto-apply
              :enable-time-picker="false"
              placeholder="Date of deposit"
              position="left"
            />
          </v-input>
          <v-btn color="primary" class="me-4" type="submit">Submit</v-btn>
          <v-btn color="red" @click="cancel">Cancel</v-btn>
        </form>
      </v-container>
    </v-card>
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
  },
};
</script>

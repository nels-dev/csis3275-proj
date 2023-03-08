<template>
  <v-dialog v-model="dialog" width="800" scrollable>
    <template v-slot:activator="{ props }">
      <v-btn class="ma-2" color="primary" v-bind="props"> New request </v-btn>
    </template>
    <form @submit.prevent="submit">
      <v-card elevation="12">
        <v-card-title>
          <span class="text-h5">New Withdrawal Request</span></v-card-title
        >
        <v-card-text style="height: 70vh">
          <v-container>
            <v-alert
              color="primary"
              variant="outlined"
              class="mb-8"
              icon="mdi-cash-refund"
            >
              <template v-slot:title> Instructions </template>
              <br />
              To withdraw from your account, please provide the remittance
              information and the amount you are going to withdraw. Your account
              balance will be debited right after you submit the request and we
              will process your instruction within 1 business day.
            </v-alert>
            <FormAlert :message="error" />
            <v-row>
              <v-col cols="12">
                <v-text-field
                  label="Amount"
                  v-model="form.amount"
                  hint="The amount you are going withdraw"
                  :error-messages="getMessage('amount')"
                  prefix="C$"
                ></v-text-field> </v-col
            ></v-row>
            <v-row>
              <v-col cols="12">
                <v-text-field
                  label="Bank Institution Number"
                  v-model="form.bankInstitutionNumber"
                  hint="3-digit bank institution number. Usually found in remittance instruction of your bank account"
                  :error-messages="getMessage('bankInstitutionNumber')"
                  maxlength="3"
                ></v-text-field> </v-col
            ></v-row>
            <v-row>
              <v-col cols="12">
                <v-text-field
                  label="Bank Transit Number"
                  v-model="form.bankTransitNumber"
                  hint="5-digit bank transit number. Usually found in remittance instruction of your bank account"
                  :error-messages="getMessage('bankTransitNumber')"
                  maxlength="5"
                ></v-text-field> </v-col
            ></v-row>
            <v-row>
              <v-col cols="12">
                <v-text-field
                  label="Bank Account Number"
                  v-model="form.bankAccountNumber"
                  hint="Your bank account number"
                  :error-messages="getMessage('bankAccountNumber')"
                ></v-text-field> </v-col
            ></v-row>
            <v-row>
              <v-col cols="12">
                <v-text-field
                  label="Name of Beneficiary"
                  v-model="form.beneficiaryName"
                  :error-messages="getMessage('beneficiaryName')"
                  hint="Name of account holder"
                ></v-text-field> </v-col
            ></v-row>
          </v-container>
        </v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>

          <v-btn color="secondary" variant="text" @click="cancel">
            Cancel
          </v-btn>
          <v-btn color="primary" variant="text" type="submit">Submit</v-btn>
        </v-card-actions>
      </v-card>
    </form>
  </v-dialog>
</template>

<script>
import accountService from "@/services/account.service";
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
        amount: "",
        beneficiaryName: "",
        bankInstitutionNumber: "",
        bankTransitNumber: "",
        bankAccountNumber: "",
      },
      dialog: false,
      error: null,
    };
  },
  validations: {
    form: {
      beneficiaryName: { required },
      bankInstitutionNumber: { required },
      bankTransitNumber: { required },
      bankAccountNumber: { required },
      amount: { minValue: minValue(10), required },
    },
  },
  name: "WithdrawalRequestForm",
  components: { FormAlert },
  methods: {
    submit() {
      this.vuelidate
        .$validate()
        .then((isFormOK) => {
          if (isFormOK) {
            return accountService.submitWithdrawalRequest(this.form);
          } else {
            return Promise.reject();
          }
        })
        .then(() => {
          this.dialog = false;
          this.$store.dispatch("account/balanceChanged");
          this.$emit("withdrawal-submitted");
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

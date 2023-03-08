<template>
  <v-form ref="form" v-model="valid">
    <FormAlert :message="error" />
    <v-text-field label="Email*" v-model="email" :rules="emailRules" />
    <v-text-field
      label="Password*"
      v-model="password"
      :rules="passwordRules"
      :append-icon="showPassword ? 'mdi-eye' : 'mdi-eye-off'"
      :type="showPassword ? 'text' : 'password'"
      @click:append="showPassword = !showPassword"
    />
    <v-text-field
      label="Confirmed Password*"
      v-model="confirmPassword"
      :rules="confirmPasswordRules"
      :append-icon="showPassword2 ? 'mdi-eye' : 'mdi-eye-off'"
      :type="showPassword2 ? 'text' : 'password'"
      @click:append="showPassword2 = !showPassword2"
    />
    <v-text-field
      label="Preferred User Name*"
      v-model="preferredUserName"
      :rules="userNameRules"
    />
    <v-text-field label="Address*" v-model="address" :rules="userNameRules" />
    <v-text-field label="Phone Number*" v-model="mobile" :rules="mobileRules" />
    <v-btn
      :disabled="!valid"
      color="primary"
      class="mr-2"
      :loading="loading"
      @click="validate"
      >Register</v-btn
    >
    <v-btn @click="$router.push('/')" variant="text">Return</v-btn>
  </v-form>
</template>

<script>
import FormAlert from "./FormAlert.vue";
import userService from "../services/user.service";

export default {
  data: () => {
    return {
      showPassword: false,
      showPassword2: false,
      loading: false,
      valid: true,
      email: "",
      password: "",
      confirmPassword: "",
      preferredUserName: "",
      address: "",
      mobile: "",
      emailRules: [
        (v) => !!v || "E-mail is required",
        (v) => /.+@.+\..+/.test(v) || "E-mail must be valid",
      ],
      passwordRules: [
        (v) => !!v || "This field cannot be empty.",
        (v) => (v && v.length > 7) || "minimum 8 characters",
      ],
      userNameRules: [
        (v) => !!v || "This field cannot be empty.",
        (v) => (v && v.length > 2) || "minimum 3 characters",
      ],
      mobileRules: [
        (v) => !!v || "Please input correct telephone number (10 digits).",
        (v) => (v && v.length > 9) || "minimum 10 characters",
        (v) => Number.isInteger(Number(v)) || "Must be a 10 digits number",
      ],
      error: "",
    };
  },
  computed: {
    confirmPasswordRules() {
      return [
        (v) => !!v || "Confirm Password is required",
        (v) => v === this.password || "Passwords do not match",
      ];
    },
  },
  methods: {
    validate() {
      this.load = true;
      this.$refs.form
        .validate()
        .then((valid) => {
          if (valid) {
            console.log("Form submitted!");
            return userService.register({
              email: this.email,
              password: this.password,
              preferredUserName: this.preferredUserName,
              address: this.address,
              mobile: this.mobile,
            });
          }
        })
        .then(() => {
          this.$router.push("/signin");
          this.$store.dispatch(
            "alert/pushInfo",
            "Account registered! You can login now!"
          );
        })
        .catch((error) => {
          this.loading = false;
          if (error.response?.status == 400) {
            console.log(error);
            this.error = error.response.data;
          }
        });
    },
  },
  components: { FormAlert },
};
</script>

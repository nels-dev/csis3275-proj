<template>
  <v-form>
    <FormAlert :message="error" />
    <v-text-field v-model="email" required label="E-mail" />
    <v-text-field
      v-model="password"
      :append-icon="showPassword ? 'mdi-eye' : 'mdi-eye-off'"
      :type="showPassword ? 'text' : 'password'"
      required
      label="Password"
      @click:append="showPassword = !showPassword"
    />
    <v-btn
      type="submit"
      color="primary"
      class="ma-2"
      :loading="loading"
      :disabled="loading"
      @click="login()"
      >Sign in</v-btn
    >
    <div>
      <h4>Don't have an account yet? Register now!</h4>
      <v-btn
        color="primary"
        variant="outlined"
        class="ma-2"
        :loading="loading"
        :disabled="loading"
        router-link
        to="/register"
        >Register</v-btn
      >
    </div>
  </v-form>
</template>
<script>
import FormAlert from "./FormAlert.vue";

export default {
  data: () => {
    return {
      showPassword: false,
      loading: false,
      email: "",
      password: "",
      error: "",
    };
  },
  methods: {
    login() {
      this.loading = true;
      this.$store
        .dispatch("auth/login", {
          email: this.email,
          password: this.password,
        })
        .then(() => {
          this.$router.push("/");
        })
        .catch((error) => {
          this.loading = false;
          if (error.response?.status == 401) {
            this.error =
              "We are unable to identify your credentials, please try again.";
          } else {
            this.$store.dispatch(
              "alert/push",
              "Sorry, we encountered error while trying to sign you in. Please try again later"
            );
          }
        });
    },
  },
  components: { FormAlert },
};
</script>

<template>
  <v-app>
    <router-view />
    <ToastAlert />
  </v-app>
</template>
<script>
import ToastAlert from "./components/ToastAlert.vue";

export default {
  name: "App",
  data: () => ({
    //
  }),
  computed: {
    loggedIn() {
      return this.$store.state.auth.status.loggedIn;
    },
  },
  watch: {
    loggedIn() {
      if (!this.loggedIn) {
        this.$router.push("/signin");
        this.$store.dispatch(
          "alert/pushInfo",
          "You have successfully logged out."
        );
      }
    },
  },
  mounted() {
    console.log("App started", this.$store.state.auth.status);
    if (!this.loggedIn) {
      console.log("Login not detected, directing to signin");
      this.$router.push("/signin");
    }
  },
  components: { ToastAlert },
};
</script>

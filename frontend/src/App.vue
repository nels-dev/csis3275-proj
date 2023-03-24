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
    if (!this.loggedIn) {
      this.$router.push("/signin");
    }
  },
  components: { ToastAlert },
};
</script>

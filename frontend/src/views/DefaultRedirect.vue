<template>Redirecting...</template>
<script>
export default {
  computed: {
    loggedIn() {
      return this.$store.state.auth.status.loggedIn;
    },
    isClient() {
      return this.$store.state.auth.user.role === "CLIENT";
    },
    isAdmin() {
      return this.$store.state.auth.user.role === "ADMIN";
    },
  },
  mounted() {
    if (this.loggedIn) {
      console.log("Login detected");
      if (this.isClient) {
        console.log("Client detected, directing to home");
        this.$router.push("/home");
      } else if (this.isAdmin) {
        console.log("Admin detected, directing to admin dashboardx");
        this.$router.push("/admin");
      }
    } else {
      console.log("Login not detected, directing to signin");
      this.$router.push("/signin");
    }
  },
};
</script>

<template>
  <v-app>
    <router-view />
  </v-app>

</template>

<script>

export default {
  name: 'App',

  data: () => ({
    //
  }),
  computed: {
    loggedIn(){
      return this.$store.state.auth.status.loggedIn
    }
  },
  mounted(){
    console.log("App started", this.$store.state.auth.status);
    if (!this.loggedIn){
      console.log("Login not detected, directing to signin");
      this.$router.push("/signin")
    }
  }
}
</script>

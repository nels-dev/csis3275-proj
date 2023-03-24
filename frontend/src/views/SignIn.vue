<template>
  <v-row class="fill-height">
    <v-col
      id="signinImage"
      class="d-none d-md-block"
      :style="{
        'background-image': `url(${require('@/assets/signin_background_2.jpg')})`,
      }"
    >
    </v-col>
    <v-col md="5" align-self="center">
      <v-container>
        <v-img
          src="../assets/seasell_logo.png"
          height="200"
          width="200"
          contain
        ></v-img>
        <h1>Welcome back to SeaSell.</h1>
        <SigninForm class="my-10" />
        <div>
          <h4>Don't have an account yet? Register now!</h4>
          <v-btn
            color="primary"
            variant="outlined"
            class="my-2"
            router-link
            to="/register"
            >Register</v-btn
          >
        </div>
      </v-container>
    </v-col>
  </v-row>
</template>

<script>
import SigninForm from "@/components/SigninForm.vue";

export default {
  components: { SigninForm },
};
</script>

<style>
#signinImage {
  background-size: cover;
  background-position: left bottom;
}
</style>

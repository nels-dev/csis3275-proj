<template>
  <v-row class="fill-height">
    <v-col
      id="signinImage"
      class="d-none d-md-block"
      :style="{
        'background-image': `url(${require('@/assets/signin_background.jpg')})`,
      }"
    >
    </v-col>
    <v-col md="4" align-self="center">
      <v-container>
        <h1>Welcome to SeaSell!</h1>
        <h2>
          To register an account, please input information required below.
        </h2>
        <RegistrationForm />
      </v-container>
    </v-col>
  </v-row>
</template>

<!-- vuetify -->
<script>
import RegistrationForm from "@/components/RegistrationForm.vue";
export default {
  components: { RegistrationForm },
};
</script>

<style>
#signinImage {
  background-size: cover;
  background-position: right bottom;
}
</style>

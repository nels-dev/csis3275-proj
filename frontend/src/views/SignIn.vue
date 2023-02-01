<template>
        <v-row class="fill-height">
          <v-col id="signinImage" class="d-none d-md-block" 
                :style="{'background-image': `url(${require('@/assets/signin_background_2.jpg')})`}">
      
          </v-col>
          <v-col md="4"  align-self="center">
            <v-container>
            <h1>Welcome back</h1>
            <SigninForm/>
            
          </v-container>
          
          </v-col>
        </v-row>
  </template>
  
  <script>
import SigninForm from '@/components/SigninForm.vue';

    export default {
    components: { SigninForm }
}
  </script>

  <style>
  #signinImage{
    background-size: cover;
    background-position: left bottom;
  }
</style>
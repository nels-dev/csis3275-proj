<template>
<v-form>
    <v-alert
      variant="outlined"
      type="warning"
      prominent
      border="top"
      v-if="error"
    >
      {{ error }}
    </v-alert>
              <v-text-field
                v-model="email"
                required
                label="E-mail"
              />
              <v-text-field
                v-model="password"
                :append-icon="showPassword ? 'mdi-eye' : 'mdi-eye-off'"
                :type="showPassword ? 'text' : 'password'"
                required
                label="Password"
                @click:append="showPassword = !showPassword"
              />
              <v-btn 
               color="primary"
               variant="outlined"
                class="ma-2" 
               :loading="loading" 
               :disabled="loading" 
               @click="login()">Sign in</v-btn>
            </v-form>
</template>
<script>
export default{
    data: ()=> {
        return {
            showPassword: false,
            loading: false,
            email: "",
            password: "",
            error: ""
        }
    },
    methods: {
        login(){
            this.loading = true
            this.$store.dispatch("auth/login", {
                email: this.email,
                password: this.password,
            }).then(()=>{
                this.$router.push("/")
            }).catch((error)=>{
                this.loading=false;
                if (error.response?.status == 401){
                    this.error = 'We are unable to identify your credentials, please try again.'
                } else {
                    this.error = 'Sorry! We encountered error while trying to sign you in.'
                }
            })
        }
    }   
}
</script>
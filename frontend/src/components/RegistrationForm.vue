<template>
    <v-form>
        <FormAlert :message="error" />
        <v-text-field v-model="email" required label="E-mail*" 
            :rules="emailRules"
        />
        <v-text-field v-model="password" :append-icon="showPassword ? 'mdi-eye' : 'mdi-eye-off'"
            :type="showPassword ? 'text' : 'password'" required label="Password*"
            @click:append="showPassword = !showPassword" 
            :rules="inputRules"
        />
        <v-text-field v-model="password" :append-icon="showPassword ? 'mdi-eye' : 'mdi-eye-off'"
            :type="showPassword ? 'text' : 'password'" required label="Confirmed Password*"
            @click:append="showPassword = !showPassword" 
            :rules="inputRules"
        />
        <v-text-field v-model="preferredUserName" required label="Preferred User Name*"/>
        <v-text-field v-model="address" required label="Address*"/>
        <v-text-field v-model="phone" required label="Phone Number*"/>

        <v-btn color="primary" variant="outlined" class="ma-2" :loading="loading" :disabled="loading"
            @click="register()">Register</v-btn>
    </v-form>
</template>

<!-- class="{'form-control':true, 'is-invalid' : !validEmail(email) && emailBlured}" v-on:blur="emailBlured = true" -->
<!-- v-bind:class="{'form-control':true, 'is-invalid' : !validPassword(password) && passwordBlured}" v-on:blur="passwordBlured = true" -->

<script>
// import userService from '@/services/user.service';
import FormAlert from './FormAlert.vue'

export default{
    data: () => {
        return {
            showPassword: false,
            loading: false,
            // fields
            email: "",    
            password: "",
            // validation
            // emailBlured: false,
            // valid: false,
            // submitted: false, 
            // passwordBlured: false,
            // 
            preferredUserName: "",
            address: "",
            mobile: "",
            inputRules:[
                v => v.length >=7 || 'Minimum length is 8 characters'
            ],
            emailRules:[
                v => !v || /(.+)@(.+){2,}\.(.+){2,}/.test(v) || 'Please input a correct email address'
            ],
            error: "",
        };
    },
    methods: {
        register() {
            this.loading = true;
            this.userService.register({
                email: this.email,
                password: this.password,
                preferredUserName: this.preferredUserName,
                address: this.address,
                mobile: this.mobile,
            }).then(() => {
                this.$router.push("/signin");
            }).catch((error) => {
                this.loading = false;
                // validataion: 1. passwords input must be the same, 2. email is in correct format(Done)
                // Bad request 400 
                if (error.response?.status == 400) {
                    this.error = "Duplicated email address! Please use another email address.";
                }
            });
        },
        // validate(){
        //     this.emailBlured = true;
        //     this.passwordBlured = true;
        //     if( this.validEmail(this.email) && this.validPassword(this.password)){
        //         this.valid = true;
        //     }
        // },
        // validEmail(email){
        //     var re = /(.+)@(.+){2,}\.(.+){2,}/;
        //     if(re.test(email.toLowerCase())){
        //         return true;
        //     } 
        // },
        // validPassword(password){
        //     if (password.length > 7) {
        //         return true;
        //     }
        // },
    },
    components: { FormAlert }
}

</script>
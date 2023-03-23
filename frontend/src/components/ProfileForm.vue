<template>
  <v-container>
    <h3 class="text-h3 mb-5">Profile</h3>
    <v-row>
      <v-col cols="12" md="6">
        <v-form @submit.prevent="save">
          <span class="text-subtitle-2 pa-2">Preferred User Name</span>
          <v-text-field v-model="profile.preferredUserName" />
          <span class="text-subtitle-2 pa-2">Address</span>
          <v-text-field v-model="profile.address" />
          <span class="text-subtitle-2 pa-2">Mobile</span>
          <v-text-field
            v-model="profile.mobile"
            :error-messages="getMessage('mobile')"
          />
          <v-btn type="submit" color="primary" class="ma-2">Save</v-btn>
        </v-form>
      </v-col>
    </v-row>
  </v-container>
</template>
<script>
import userService from "@/services/user.service";
import { useVuelidate } from "@vuelidate/core";
import { maxLength, numeric } from "@vuelidate/validators";

export default {
  name: "ProfileForm",
  data: () => ({
    profile: {
      preferredUserName: "",
      address: "",
      mobile: "",
    },
  }),
  mounted() {
    userService.getCurrentClientUser().then((resp) => {
      this.profile = resp.data;
    });
  },
  setup() {
    return { vuelidate: useVuelidate() };
  },
  validations: {
    profile: {
      mobile: {
        maxLength: maxLength(10),
        numeric: numeric,
      },
    },
  },
  methods: {
    save() {
      this.vuelidate
        .$validate()
        .then((valid) => {
          if (valid) {
            return userService.saveUserProfile(this.profile);
          } else {
            return Promise.reject();
          }
        })
        .then(() => {
          this.$store.dispatch("alert/pushInfo", "Your profile has been saved");
        })
        .catch((error) => {
          if (error.response?.status != 200) {
            console.log(error);
            this.error = error.response.data;
          }
        });
    },
    getMessage(field) {
      return this.vuelidate.profile[field].$errors.map((e) => e.$message);
    },
  },
};
</script>

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
          <v-text-field v-model="profile.mobile" />
          <v-btn type="submit" color="primary" class="ma-2">Save</v-btn>
        </v-form>
      </v-col>
    </v-row>
  </v-container>
</template>
<script>
import userService from "@/services/user.service";

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
  methods: {
    save() {
      userService.saveUserProfile(this.profile).then(() => {
        this.$store.dispatch("alert/pushInfo", "Your profile has been saved");
      });
    },
  },
};
</script>

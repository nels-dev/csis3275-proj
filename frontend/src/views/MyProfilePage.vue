<template>
  <HeaderPage />
  <v-main>
    <ProfileForm />
  </v-main>
  <FooterPage />
</template>
<script>
import ProfileForm from "../components/ProfileForm.vue";
import HeaderPage from "@/components/HeaderPage.vue";
import FooterPage from "@/components/FooterPage.vue";
export default {
  name: "MyProfilePage",
  components: { HeaderPage, ProfileForm, FooterPage },
};
</script>

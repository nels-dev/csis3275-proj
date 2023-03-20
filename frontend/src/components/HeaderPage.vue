<template>
  <v-app-bar height="80" elevation="1">
    <v-img
      src="../assets/seasell_logo.png"
      max-height="60"
      max-width="60"
      contain
      class="ml-8 mr-5"
      type="button"
      @click="$router.push('/Home')"
    ></v-img>

    <v-img
      src="../assets/title.svg"
      contain
      max-height="50"
      max-width="200"
      type="button"
      @click="$router.push('/Home')"
    ></v-img>
    <v-spacer></v-spacer>
    <v-btn @click="$router.push('/Home')"> Marketplace</v-btn>
    <v-btn @click="$router.push('/mystore')"> My Store </v-btn>
    <v-btn @click="$router.push('/myorder')"> Orders </v-btn>
    <v-spacer></v-spacer>
    <CreditChip />

    <v-btn icon class="mr-8">
      <!-- start of avatar -->

      <v-menu min-width="200px" rounded>
        <template v-slot:activator="{ props }">
          <v-btn icon v-bind="props">
            <v-avatar color="primary">
              <span class="text-h5">{{ user.initials }}</span>
            </v-avatar>
          </v-btn>
        </template>
        <v-card>
          <v-card-text>
            <div class="mx-auto text-center">
              <v-avatar
                type="button"
                class="my-2"
                @click="$router.push('/profile')"
                color="primary"
              >
                <span class="text-h5">{{ user.initials }}</span>
              </v-avatar>
              <v-btn variant="text" block @click="$router.push('/profile')">{{
                user.fullName
              }}</v-btn>
              <p class="text-caption mt-1">
                {{ user.email }}
              </p>
              <v-divider class="my-3"></v-divider>
              <v-btn rounded variant="text" @click="logout()"> Logout </v-btn>
            </div>
          </v-card-text>
        </v-card>
      </v-menu>

      <!-- end of avatar -->
    </v-btn>
  </v-app-bar>
</template>
<script>
import userService from "@/services/user.service";
import CreditChip from "@/components/CreditChip";
export default {
  //  start of slideshow
  data() {
    return {
      user: {
        initials: "",
        fullName: "",
        email: "",
        loading: false,
      },
    };
  },
  methods: {
    logout() {
      this.$store.dispatch("auth/logout");
    },
  },

  components: { CreditChip },
  mounted() {
    userService.getCurrentClientUser().then((resp) => {
      this.user.fullName = resp.data.preferredUserName;
      this.user.email = resp.data.email;
      this.user.initials = resp.data.preferredUserName[0];
    });
  },
};
</script>

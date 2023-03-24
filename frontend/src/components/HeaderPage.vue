<template>
  <v-app-bar elevation="1">
    <v-container>
      <v-row class="align-center">
        <v-col class="flex-grow-0">
          <v-img
            src="../assets/seasell_logo.png"
            height="60"
            width="60"
            contain
            type="button"
            @click="$router.push('/client/home')"
          ></v-img>
        </v-col>
        <v-col class="flex-grow-0 flex-shrink-1 d-none d-md-block">
          <v-img
            src="../assets/title.svg"
            contain
            height="60"
            width="130"
            type="button"
            @click="$router.push('/client/home')"
          ></v-img>
        </v-col>

        <v-col class="flex-grow-1" align="center">
          <v-btn variant="flat" size="small" to="/client/marketplace">
            Marketplace</v-btn
          >
          <v-btn variant="flat" size="small" to="/client/mystore">
            My Store
          </v-btn>
          <v-btn variant="flat" size="small" to="/client/myorder">
            Orders
          </v-btn>
        </v-col>

        <v-col class="flex-grow-0">
          <CreditChip />
        </v-col>
        <v-col class="flex-grow-0">
          <v-btn icon>
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
                      @click="$router.push('/client/profile')"
                      color="primary"
                    >
                      <span class="text-h5">{{ user.initials }}</span>
                    </v-avatar>
                    <v-btn variant="text" block to="/client/profile">{{
                      user.fullName
                    }}</v-btn>
                    <p class="text-caption mt-1">
                      {{ user.email }}
                    </p>
                    <v-divider class="my-3"></v-divider>
                    <v-btn rounded variant="text" @click="logout()">
                      Logout
                    </v-btn>
                  </div>
                </v-card-text>
              </v-card>
            </v-menu>

            <!-- end of avatar -->
          </v-btn>
        </v-col>
      </v-row>
    </v-container>
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

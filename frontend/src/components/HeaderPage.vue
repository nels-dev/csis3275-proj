<template>
  <v-app-bar app shrink-on-scroll>
    <v-img
      class="mx-2"
      src="../assets/seasell_logo.svg"
      max-height="50"
      max-width="50"
      contain
      @click="goMainPage()"
    ></v-img>
    <v-toolbar-title type="button" @click="$router.push('/Home')"
      >SeaSell</v-toolbar-title
    >
    <v-spacer></v-spacer>
    <v-btn class="mx-2" size="large" @click="$router.push('/mystore')">
      <font-awesome-icon icon="fa-solid fa-shop" size="2x" />
      My Store
    </v-btn>
    <CreditChip />
    <v-btn icon>
      <!-- start of avatar -->
      <v-container fluid style="height: 300px">
        <v-row justify="center">
          <v-menu min-width="200px" rounded>
            <template v-slot:activator="{ props }">
              <v-btn icon v-bind="props">
                <v-avatar color="primary" size="large">
                  <span class="text-h5">{{ user.initials }}</span>
                </v-avatar>
              </v-btn>
            </template>
            <v-card>
              <v-card-text>
                <div class="mx-auto text-center">
                  <v-avatar color="primary">
                    <span class="text-h5">{{ user.initials }}</span>
                  </v-avatar>
                  <h3>{{ user.fullName }}</h3>
                  <p class="text-caption mt-1">
                    {{ user.email }}
                  </p>
                  <v-divider class="my-3"></v-divider>
                  <v-btn
                    rounded
                    variant="text"
                    @click="$router.push('/mystore')"
                  >
                    My store
                  </v-btn>
                  <v-divider class="my-3"></v-divider>
                  <v-btn rounded variant="text"> My order </v-btn>
                  <v-divider class="my-3"></v-divider>
                  <v-btn rounded variant="text" @click="logout()">
                    Logout
                  </v-btn>
                </div>
              </v-card-text>
            </v-card>
          </v-menu>
        </v-row>
      </v-container>

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

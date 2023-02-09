<template>
  <v-app-bar app shrink-on-scroll>
    <v-toolbar-title class="color:teal lighten-4" @click="$router.push('/Home')"
      >SeaSell</v-toolbar-title
    >
    <v-img
      src="../assets/seasell_logo.svg"
      height="200px"
      @click="$router.push('/Home')"
    ></v-img>

    <v-spacer></v-spacer>
    <v-btn icon>
      <!-- start of avatar -->
      <v-container fluid style="height: 300px">
        <v-row justify="center">
          <v-menu min-width="200px" rounded>
            <template v-slot:activator="{ props }">
              <v-btn icon v-bind="props">
                <v-avatar color="brown" size="large">
                  <span class="text-h5">{{ user.initials }}</span>
                </v-avatar>
              </v-btn>
            </template>
            <v-card>
              <v-card-text>
                <div class="mx-auto text-center">
                  <v-avatar color="brown">
                    <span class="text-h5">{{ user.initials }}</span>
                  </v-avatar>
                  <h3>{{ user.fullName }}</h3>
                  <p class="text-caption mt-1">
                    {{ user.email }}
                  </p>
                  <v-divider class="my-3"></v-divider>
                  <v-btn rounded variant="text"> My store </v-btn>
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

  components: {},
  mounted() {
    userService.getCurrentClientUser().then((resp) => {
      this.user.fullName = resp.data.preferredUserName;
      this.user.email = resp.data.email;
      this.user.initials =
        resp.data.preferredUserName[0] +
        resp.data.preferredUserName[
          resp.data.preferredUserName.indexOf(" ") + 1
        ];
    });
  },
};
</script>

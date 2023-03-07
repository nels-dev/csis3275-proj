<template>
  <v-app id="inspire">
    <v-app-bar app shrink-on-scroll>
      <v-img
        src="../assets/seasell_logo.png"
        max-height="60"
        max-width="60"
        contain
        class="ml-8 mr-5"
        type="button"
        @click="$router.push('/admin')"
      ></v-img>
      <v-toolbar-title>Admin Dashboard</v-toolbar-title>
      <v-spacer></v-spacer>
      <v-btn icon class="mr-8">
        <!-- start of avatar -->
        <v-menu min-width="200px" rounded>
          <template v-slot:activator="{ props }">
            <v-btn icon v-bind="props">
              <v-avatar color="info">
                <v-icon icon="mdi-account-circle"></v-icon>
              </v-avatar>
            </v-btn>
          </template>
          <v-card>
            <v-card-text>
              <div class="mx-auto text-center">
                <v-avatar color="info">
                  <v-icon icon="mdi-account-circle"></v-icon>
                </v-avatar>
                <h3>Administrator</h3>
                <v-divider class="my-3"></v-divider>
                <v-btn rounded variant="text" @click="logout"> Logout </v-btn>
              </div>
            </v-card-text>
          </v-card>
        </v-menu>

        <!-- end of avatar -->
      </v-btn>
    </v-app-bar>

    <v-main>
      <v-tabs v-model="tab" bg-color="secondary">
        <v-tab value="DEPOSITS">DEPOSITS</v-tab>
        <v-tab value="WITHDRAWALS">WITHDRAWALS</v-tab>
      </v-tabs>
      <v-window v-model="tab">
        <v-window-item value="DEPOSITS">
          <AdminDeposits></AdminDeposits>
        </v-window-item>
        <v-window-item value="WITHDRAWALS">
          <AdminWithdrawals></AdminWithdrawals>
        </v-window-item>
      </v-window>
    </v-main>
  </v-app>
</template>

<script>
import AdminDeposits from "@/components/AdminDeposits.vue";
import AdminWithdrawals from "@/components/AdminWithdrawals.vue";
export default {
  components: { AdminDeposits, AdminWithdrawals },
  data: () => ({
    tab: null,
  }),
  methods: {
    logout() {
      this.$store.dispatch("auth/logout");
    },
  },
};
</script>

<!-- eslint-disable prettier/prettier -->
<template>
  <v-container>
    <div v-if="notEmpty">
      <v-table class="elevation-2">
        <thead>
          <tr>
            <th class="text-left">
              Order Date
            </th>
            <th class="text-left">
              Product Name
            </th>
            <th class="text-left">
              Current Status
            </th>
            <th class="text-left">
              Reference Number
            </th>
            <th class="text-left">
              Action
            </th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="item in listings" :key="item.id">
            <td>{{ moment(item.orderTime).format("MMMM Do YYYY") }}</td>
            <td>{{ item.productName }} <v-icon @click="this.$router.push(`/client/productdetails/${item.productId}`)" icon="mdi-open-in-new"></v-icon></td>
            <td>
              <v-chip class="ma-2" color="success" variant="outlined">
                <span class="text-subtitle-2">{{ item.status }}</span>
              </v-chip>
            </td>
            <td>{{ item.shipmentReference }}</td>
            <td>

              <v-dialog v-model="dialog" width="600">
                <template v-slot:activator="{ props }">
                  <v-btn class="ma-2" v-bind="props" color="primary" v-if="item.status === 'DELIVERED'">
                    Confirm Order
                    <v-icon end icon="mdi-checkbox-marked-circle"></v-icon>
                  </v-btn>
                </template>
                <v-card>
                  <v-card-title>
                    <span class="text-h5">Confirm</span>
                  </v-card-title>
                  <v-card-text>
                    <v-alert prominent color="primary" variant="outlined" class="mb-8" icon="mdi-check-circle">
                      Just a quick reminder that when you confirm your order, you're letting us know that you've received
                      the item and that it's in the same condition as we described. This helps us ensure that you're happy
                      with your purchase. Once you've confirmed your order, we'll release the on-hold credit to the
                      seller.
                    </v-alert>

                  </v-card-text>
                  <v-card-actions>
                    <v-spacer></v-spacer>
                    <v-btn color="secondary" variant="text" @click="dialog = false">
                      Cancel
                    </v-btn>
                    <v-btn color="primary" variant="text" @click="confirm(item.id)">
                      Confirm
                    </v-btn>
                  </v-card-actions>
                </v-card>
              </v-dialog>
            </td>
          </tr>
        </tbody>
      </v-table>
    </div>
    <div v-else>
      <h1>You have not placed any order</h1>
    </div>
  </v-container>
</template>
<script>
import orderService from "@/services/order.service";
export default {
  inject: ["moment"],
  data() {
    return {
      notEmpty: true,
      listings: [],
      dialog: false,
    };
  },
  mounted() {
    this.refreshData();
  },
  methods: {
    confirm(id) {
      orderService
        .updateOrderStatus(id, "COMPLETED")
        .then(() => {
          this.$store.dispatch("alert/pushInfo", "Order confirmed");
          this.refreshData();
          this.dialog = false;
        })
        .catch(() => {
          this.dialog = false;
          this.$store.dispatch(
            "alert/push",
            "Sorry, we encountered error while trying to confirm your order. Please try again later"
          );
        });
    },
    refreshData() {
      orderService.getOrders().then((response) => {
        const orderedOrders = response.data;
        if (orderedOrders.length > 0) {
          this.listings = orderedOrders;
        } else {
          this.notEmpty = false;
        }
      });
    },
  },
};
</script>

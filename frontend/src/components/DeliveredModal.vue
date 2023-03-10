<template>
  <v-dialog v-model="showdialog" persistent max-width="900">
    <v-card>
      <v-card-title class="bg-cyan-darken-1 text-h4">Deliver</v-card-title>
      <v-card
        variant="tonal"
        height="50"
        class="d-flex align-center text-body-1"
      >
        <span class="pa-5">Product name: </span>
        <span
          ><b>{{ item.name }}</b></span
        >
      </v-card>
      <v-container>
        <span>Detailed product description: </span>
        <span
          ><b>{{ item.description }}</b></span
        >
      </v-container>
      <v-container>
        <span>Product price: C$</span>
        <span
          ><b>{{ item.price }}</b></span
        >
      </v-container>
      <v-container>
        <span>Product condition: </span>
        <span
          ><b>{{ item.condition }}</b></span
        >
      </v-container>
      <v-container>
        <span>Mode of delivery(included): </span>
        <span><b>Standard Shipment</b></span>
      </v-container>

      <v-divider></v-divider>

      <v-container>
        <v-text-field
          hide-details="auto"
          label="Shippment Reference"
          placeholder="S000000000"
          >{{ ShippmentReference }}</v-text-field
        >
      </v-container>
      <v-card-actions>
        <v-btn
          color="green"
          @click="confirmDelivered"
          :disabled="availableBalance < item.price"
          >Ship</v-btn
        >
        <v-btn color="red" @click="cancelDelivered">Cancel</v-btn>
      </v-card-actions>
    </v-card>
  </v-dialog>
</template>

<script>
import accountService from "@/services/account.service";
import orderService from "@/services/order.service";
import router from "@/router";

export default {
  props: {
    item: Object,
    dialog: Boolean,
  },
  data: () => ({
    availableBalance: 0,
    // ShippmentReference=orderService.getOrder(),
  }),
  methods: {
    confirmDelivered() {
      const productId = this.item.id;
      orderService.addOrder(productId).then(() => {
        this.$emit("checkout-confirmed");
        this.$store.dispatch("account/balanceChanged");
        this.$store.dispatch("alert/pushInfo", "Checkout successful!");
        router.push("/Home");
        this.showDialog = false;
      });
    },
    cancelDelivered() {
      this.$emit("Delivered-canceled");
      this.showDialog = false;
    },
  },
  mounted() {
    accountService.getAccount().then((resp) => {
      this.availableBalance = resp.data?.availableBalance ?? 0;
    });
  },
};
</script>

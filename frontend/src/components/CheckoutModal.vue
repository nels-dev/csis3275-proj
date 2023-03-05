<template>
  <v-dialog v-model="showdialog" persistent max-width="900">
    <v-card>
      <v-card-title class="bg-cyan-darken-1 text-h4">Checkout</v-card-title>
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

      <v-card
        class="d-flex flex-row-reverse mb-2 text-decoration-underline elevation-3 red--text"
      >
        <v-card-text style="color: red; text-align: right"
          >Credit to be deducted C${{ item.price }}</v-card-text
        >
      </v-card>
      <v-card
        class="d-flex flex-row-reverse mb-2 text-decoration-underline elevation-3 red--text"
      >
        <v-card-text style="color: green; text-align: right"
          >Current credit balance: C${{
            availableBalance.toFixed(2)
          }}</v-card-text
        >
      </v-card>
      <v-checkbox
        :label="`I agree to deduct C$${item.price} amount from my credit balance`"
      ></v-checkbox>
      <v-card-actions>
        <v-btn
          color="green"
          @click="confirmCheckout"
          :disabled="availableBalance < item.price"
          >Confirm</v-btn
        >
        <v-btn color="red" @click="cancelCheckout">Cancel</v-btn>
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
    // showDialog: this.dialog,
    availableBalance: 0,
  }),
  methods: {
    confirmCheckout() {
      const Id = this.item.id;
      orderService.addOrder(Id);
      console.log(Id);
      this.$emit("checkout-confirmed");
      router.push("/Home");
      this.showDialog = false;
    },
    cancelCheckout() {
      this.$emit("checkout-canceled");
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

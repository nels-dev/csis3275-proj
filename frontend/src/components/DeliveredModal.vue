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
          ref="shipmentRef"
          hide-details="auto"
          placeholder="Shippment Reference"
          v-model="shipmentReference"
        ></v-text-field>
      </v-container>
      <v-card-actions>
        <v-btn color="green" @click="confirmDelivered">Ship</v-btn>
        <v-btn color="red" @click="cancelDelivered">Cancel</v-btn>
      </v-card-actions>
    </v-card>
  </v-dialog>
</template>

<script>
import orderService from "@/services/order.service";
import router from "@/router";

export default {
  data() {
    return {
      shipmentReference: this.item.id,
    };
  },
  props: {
    item: Object,
    dialog: Boolean,
  },
  methods: {
    confirmDelivered() {
      const shippmentReference = this.$refs.shipmentRef.value;
      orderService.updateOrder(1, shippmentReference, "DELIVERED").then(() => {
        this.$emit("ship-confirmed");
        this.$store.dispatch(
          "alert/pushInfo",
          "Update status to delivered successful!"
        );
        router.push("/Home");
        this.showDialog = false;
      });
    },
    cancelDelivered() {
      this.$emit("Delivered-canceled");
      this.showDialog = false;
    },
  },
};
</script>

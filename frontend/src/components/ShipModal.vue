<template>
  <v-dialog v-model="showdialog" persistent max-width="900">
    <v-card>
      <v-card-title class="bg-cyan-darken-1 text-h4">Ship</v-card-title>
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
          v-model="shipmentReference"
          hide-details="auto"
          placeholder="S000000000"
        ></v-text-field>
      </v-container>
      <v-card-actions>
        <v-btn color="green" @click="confirmShip">Ship</v-btn>
        <v-btn color="red" @click="cancelShip">Cancel</v-btn>
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
      shipmentReference: "",
    };
  },
  props: {
    item: Object,
    dialog: Boolean,
  },
  methods: {
    confirmShip() {
      orderService
        .updateOrderStatusWithShipmentReference(
          this.item.activeOrder,
          "SHIPPED",
          this.shipmentReference
        )
        .then(() => {
          this.$emit("ship-confirmed");
          this.$store.dispatch(
            "alert/pushInfo",
            "Update status to shipped successful!"
          );
          router.push("/Home");
          this.showDialog = false;
        });
    },
    cancelShip() {
      this.$emit("ship-canceled");
      this.showDialog = false;
    },
  },
};
</script>

<template>
  <!-- start of the card -->
  <v-card>
    <v-img
      :src="imageUrl"
      height="250"
      @click="$router.push('/productdetails/' + item.id)"
      type="button"
    >
      <v-card-title
        v-text="item.status"
        style="
          position: absolute;
          bottom: 0;
          width: 100%;
          background-color: rgba(0, 0, 0, 0.5);
          font-size: 12px;
          color: white;
          line-height: 1.1;
        "
      ></v-card-title>
    </v-img>
    <v-card-title @click="$router.push('/productdetails/' + item.id)">
      {{ item.name }}
    </v-card-title>
    <v-card-subtitle> {{ item.description }} </v-card-subtitle>
    <v-card-text>
      <v-row no-gutters>
        <v-col class="text-h6"> C${{ item.price?.toFixed(2) }} </v-col>
      </v-row>
    </v-card-text>
    <v-card-actions>
      <v-btn
        color="primary"
        text
        v-if="item.status === 'UNLISTED'"
        @click="toggleProductStatus(item.id, 'LISTED')"
      >
        List
      </v-btn>
      <v-btn
        color="primary"
        text
        v-if="item.status === 'LISTED'"
        @click="toggleProductStatus(item.id, 'UNLISTED')"
      >
        Unlist
      </v-btn>
      <v-btn
        color="primary"
        text
        v-if="item.status === 'ORDERED'"
        @click="showShipDialog = true"
      >
        Ship
      </v-btn>
      <v-btn
        color="primary"
        text
        v-if="item.status === 'SHIPPED'"
        @click="showDeliveredDialog = true"
      >
        Deliver
      </v-btn>
      <v-spacer></v-spacer>
      <v-btn
        v-if="['LISTED', 'UNLISTED'].includes(item.status)"
        @click="$router.push('/editProduct/' + item.id)"
      >
        <v-icon icon="mdi-square-edit-outline" />
      </v-btn>
    </v-card-actions>
  </v-card>
  <ShipModal
    :item="item"
    v-model="showShipDialog"
    @ship-confirmed="confirmShip"
    @ship-canceled="cancelShip"
  />
  <DeliveredModal
    :item="item"
    v-model="showDeliveredDialog"
    @delivered-confirmed="confirmDelivered"
    @delivered-canceled="cancelDelivered"
  />
  <!-- end of the card -->
</template>
<script>
import DeliveredModal from "./DeliveredModal";
import ShipModal from "./ShipModal";
import storeService from "@/services/store.service";

export default {
  data: () => ({
    showShipDialog: false,
    showDeliveredDialog: false,
  }),
  components: {
    ShipModal,
    DeliveredModal,
  },
  props: ["item"],
  computed: {
    imageUrl() {
      return process.env.VUE_APP_API_URL + "/images/" + this.item.images[0];
    },
  },
  methods: {
    toggleProductStatus(id, newStatus) {
      storeService.editProductStatus(id, newStatus).then(() => {
        this.$store.dispatch("mystore/productUpdated");
        this.$store.dispatch("alert/pushInfo", "Product status changed");
      });
    },
    confirmShip() {
      this.showShipDialog = false;
    },
    cancelShip() {
      this.showShipDialog = false;
    },
    confirmDelivered() {
      this.showDeliveredDialog = false;
    },
    cancelDelivered() {
      this.showDeliveredDialog = false;
    },
  },
};
</script>

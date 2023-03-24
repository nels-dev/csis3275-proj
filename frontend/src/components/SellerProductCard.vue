<template>
  <!-- start of the card -->
  <v-card height="100%">
    <v-img
      :src="imageUrl"
      aspect-ratio="1.778"
      @click="$router.push('/client/productdetails/' + item.id)"
      type="button"
    >
    </v-img>
    <v-card-title @click="$router.push('/client/productdetails/' + item.id)">
      <span class="text-info font-weight-bold">{{ item.name }}</span>
      <v-chip class="mx-2 float-right" :color="contextColor(item.status)">
        <span class="text-subtitle-2">{{ item.status }}</span>
      </v-chip>
    </v-card-title>
    <v-card-subtitle> {{ item.description }} </v-card-subtitle>
    <v-card-text>
      <v-row no-gutters>
        <v-col class="text-subtitle-1"> C${{ item.price?.toFixed(2) }} </v-col>
      </v-row>
    </v-card-text>
    <v-divider></v-divider>
    <v-card-actions>
      <v-btn
        color="primary"
        v-if="item.status === 'UNLISTED'"
        @click="toggleProductStatus(item.id, 'LISTED')"
      >
        List
      </v-btn>
      <v-btn
        color="primary"
        v-if="item.status === 'LISTED'"
        @click="toggleProductStatus(item.id, 'UNLISTED')"
      >
        Unlist
      </v-btn>
      <v-btn
        color="primary"
        v-if="item.status === 'ORDERED'"
        @click="showShipDialog = true"
      >
        Ship
      </v-btn>
      <v-btn
        color="primary"
        v-if="item.status === 'SHIPPED'"
        @click="showDeliveredDialog = true"
      >
        Deliver
      </v-btn>
      <v-spacer></v-spacer>
      <v-btn
        v-if="['LISTED', 'UNLISTED'].includes(item.status)"
        icon="mdi-square-edit-outline"
        color="primary"
        @click="$router.push('/client/editProduct/' + item.id)"
      >
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
    contextColor(status) {
      if (status == "LISTED") {
        return "blue-darken-4";
      } else if (status == "UNLISTED") {
        return "grey-darken-4";
      } else if (status == "ORDERED" || status == "SHIPPED") {
        return "amber-darken-4";
      } else if (status == "DELIVERED" || status == "SOLD") {
        return "green-darken-4";
      }
    },
  },
};
</script>

<template>
  <v-dialog v-model="showdialog" max-width="900">
    <v-card>
      <v-card-title class="bg-cyan-darken-1 text-h4">Buyer Info</v-card-title>
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
        <span>Buyer Email: </span>
        <span
          ><b>{{ buyerInfo.buyerEmail }}</b></span
        >
      </v-container>
      <v-container>
        <span>Buyer Address</span>
        <span v-if="buyerInfo.buyerAddress"
          ><b>{{ buyerInfo.buyerAddress }}</b></span
        >
        <span v-else class="text-red-lighten-1"
          ><b> Buyer's address has not been provided yet</b></span
        >
      </v-container>
      <v-container>
        <span>Order Date: </span>
        <span
          ><b>{{ buyerInfo.orderTime.slice(0, 10) }}</b></span
        >
      </v-container>
    </v-card>
  </v-dialog>
</template>

<script>
import storeService from "@/services/store.service";

export default {
  props: {
    item: Object,
    dialog: Boolean,
  },
  data() {
    return {
      buyerInfo: [],
    };
  },
  methods: {},
  created() {
    console.log(this.item.id, "CREATED");
  },
  beforeUpdate() {
    storeService.getBuyerInfoByProductId(this.item.id).then((resp) => {
      console.log(resp.data);
      this.buyerInfo = resp.data;
    });
  },
  mounted() {
    // console.log(this.item.id, "Mounted");
    // storeService.getBuyerInfoByProductId(this.item.id).then((resp) => {
    //   console.log(resp.data);
    //   this.buyerInfo = resp.data;
    // });
  },
};
</script>

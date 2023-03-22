<template>
  <v-container>
    <v-row>
      <v-col md="5" sm="12" cols="12">
        <v-img
          class="py-3"
          v-if="!!imageUrl"
          :src="imageUrl"
          max-height="400px"
          scale-down
        ></v-img>
      </v-col>
      <v-col md="7" sm="12" cols="12">
        <div class="text-h3 py-3">{{ item.name }}</div>
        <div class="text-h6">C${{ item.price?.toFixed(2) }}</div>
        <div class="pt-5">{{ item.description }}</div>
        <div class="text-subtitle-2 pt-5">Product condition</div>
        <div>{{ item.condition }}</div>
        <div class="text-subtitle-2 pt-5">Mode of delivery</div>
        <div>Shipment</div>

        <CurrentOrderBox :productId="item.id" />

        <v-spacer class="pt-10" />
        <v-btn
          color="primary "
          @click="showCheckoutDialog = true"
          v-if="item.status === 'LISTED' && item.sellerId !== currentUserId"
        >
          Checkout Now
        </v-btn>
        <v-btn
          class="ml-auto"
          color="primary "
          @click="showBuyerInfoDialog = true"
        >
          BUYER INFO
        </v-btn>
      </v-col>
    </v-row>
  </v-container>
  <v-sheet width="1000" class="mx-auto">
    <CheckoutModal
      :item="item"
      v-model="showCheckoutDialog"
      @checkout-confirmed="confirmCheckout"
      @checkout-canceled="cancelCheckout"
    />
    <BuyerInfoModal :item="item" v-model="showBuyerInfoDialog" />
  </v-sheet>
</template>
<script>
import CheckoutModal from "./CheckoutModal.vue";
import BuyerInfoModal from "./BuyerInfoModal.vue";
import CurrentOrderBox from "./CurrentOrderBox.vue";

export default {
  props: ["item"],
  components: {
    CheckoutModal,
    BuyerInfoModal,
    CurrentOrderBox,
  },
  computed: {
    imageUrl() {
      return this.item.images
        ? process.env.VUE_APP_API_URL + "/images/" + this.item.images
        : "";
    },
    currentUserId() {
      return this.$store.state.auth.user.id;
    },
  },
  data: () => ({
    showCheckoutDialog: false,
    showBuyerInfoDialog: false,
    firstName: "",
    firstNameRules: [
      (value) => {
        if (value?.length > 3) return true;
        return "First name must be at least 3 characters.";
      },
    ],
    lastName: "123",
    lastNameRules: [
      (value) => {
        if (/[^0-9]/.test(value)) return true;
        return "Last name can not contain digits.";
      },
    ],
    // currentUser: null,
  }),
  methods: {
    confirmCheckout() {
      this.showCheckoutDialog = false;
    },
    cancelCheckout() {
      this.showCheckoutDialog = false;
    },
  },
};
</script>

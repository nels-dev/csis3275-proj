<template>
  <v-sheet width="1000" class="mx-auto">
    <v-form disabled>
      Product name:
      <v-text-field :value="item.name"></v-text-field>
      Detailed product description:
      <v-text-field :value="item.description"></v-text-field>
      Price:
      <v-text-field :value="item.price?.toFixed(2)"></v-text-field>
      Product image(s):
      <v-img :src="imageUrl" height="200px" scale-down></v-img>
      Condition:
      <v-text-field :value="item.condition"></v-text-field>
      Mode of delivery (assumed standard charge $30):
      <v-text-field value="Shippment"></v-text-field>
      <v-btn color="primary " @click="showCheckoutDialog = true">
        Checkout Now
      </v-btn>
      <v-btn @click="$router.push('/Home')" variant="text">Return</v-btn>
    </v-form>
    <CheckoutModal
      :item="item"
      v-model="showCheckoutDialog"
      @checkout-confirmed="confirmCheckout"
      @checkout-canceled="cancelCheckout"
    />
  </v-sheet>
</template>
<script>
import CheckoutModal from "./CheckoutModal.vue";
export default {
  props: ["item"],
  components: {
    CheckoutModal,
  },
  computed: {
    imageUrl() {
      return process.env.VUE_APP_API_URL + "/images/" + this.item.images;
    },
  },
  data: () => ({
    showCheckoutDialog: false,
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

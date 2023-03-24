<template>
  <v-dialog v-model="showdialog" persistent max-width="1000">
    <v-card>
      <v-card-title class="bg-secondary-darken-1 text-h5"
        >Checkout</v-card-title
      >
      <v-container>
        <v-row>
          <v-col cols="12" sm="4" class="d-flex justify-center">
            <v-img
              v-if="!!imageUrl"
              :src="imageUrl"
              height="200px"
              scale-down
              style="max-width: 100%"
              class="align-self-center"
            ></v-img>
          </v-col>
          <v-col cols="12" sm="4">
            <div class="text-h5 py-3">
              <strong>{{ item.name }}</strong>
            </div>
            <div class="text-h6">C${{ item.price?.toFixed(2) }}</div>
            <div class="pt-5">{{ item.description }}</div>
            <div class="text-subtitle-2 pt-5">Product condition</div>
            <div>{{ item.condition }}</div>
            <div class="text-subtitle-2 pt-5">Mode of delivery</div>
            <div>Standard Shipment</div>
          </v-col>
          <v-col cols="12" sm="4">
            <div class="text-h5 py-3"><strong>Credit:</strong></div>
            <v-sheet class="ma-2 pa-2">
              <span>Current credit balance: </span>
              <span
                ><b>C${{ availableBalance.toFixed(2) }}</b></span
              >
            </v-sheet>
            <v-sheet class="ma-2 pa-2">
              <span>Credit to be deducted: </span>
              <span
                ><b>C${{ item.price.toFixed(2) }}</b></span
              >
            </v-sheet>
            <v-divider></v-divider>
            <v-sheet class="ma-2 pa-2">
              <span>New credit balance: </span>
              <span
                ><b>C${{ (availableBalance - item.price).toFixed(2) }}</b></span
              >
            </v-sheet>
            <v-checkbox
              :label="`I agree to deduct C$${item.price.toFixed(
                2
              )} amount from my credit balance`"
              v-model="checkboxValue"
            ></v-checkbox>
            <v-card-actions>
              <v-btn
                variant="elevated"
                color="primary"
                @click="confirmCheckout"
                :disabled="availableBalance < item.price || !checkboxValue"
              >
                Confirm
              </v-btn>
              <v-btn color="secondary" @click="cancelCheckout">Cancel</v-btn>
            </v-card-actions>
          </v-col>
        </v-row>
      </v-container>
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
    checkboxValue: false,
  }),
  computed: {
    imageUrl() {
      return this.item.images
        ? process.env.VUE_APP_API_URL + "/images/" + this.item.images
        : "";
    },
  },
  methods: {
    confirmCheckout() {
      const productId = this.item.id;
      orderService.addOrder(productId).then(() => {
        this.$emit("checkout-confirmed");
        this.$store.dispatch("account/balanceChanged");
        this.$store.dispatch("alert/pushInfo", "Checkout successful!");
        router.push("/client/home");
        this.showDialog = false;
      });
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

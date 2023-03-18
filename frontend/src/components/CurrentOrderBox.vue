<template>
  <v-card class="my-10" v-if="show">
    <v-card-title>
      Your order

      <v-chip class="ma-2" color="success" variant="outlined">
        {{ order.status }}
      </v-chip>
    </v-card-title>
    <v-card-text>
      You ordered this item {{ moment(order.orderTime).fromNow() }}

      <div class="text-subtitle-2 pt-5">Shippment reference</div>
      <div>{{ order.shipmentReference || "-" }}</div>
      <div class="text-subtitle-2 pt-5">Last updated</div>
      <div>{{ moment(order.lastUpdatedAt).fromNow() }}</div>
    </v-card-text>
  </v-card>
</template>
<script>
import orderService from "@/services/order.service";

export default {
  props: ["productId"],
  inject: ["moment"],
  data: () => ({
    order: {},
    show: false,
  }),
  mounted() {
    this.productId && this.getOrder();
  },
  watch: {
    productId() {
      this.getOrder();
    },
  },
  methods: {
    getOrder() {
      orderService.getOrdersByProductId(this.productId).then((resp) => {
        if (resp.data && resp.data[0]) {
          this.order = resp.data[0];
          this.show = true;
        }
      });
    },
  },
};
</script>

<!-- eslint-disable prettier/prettier -->
<template>
    <v-container>
    <div v-if ="notEmpty">
        <v-table class="elevation-2">
          <thead>
            <tr>
              <th class="text-left">
                Order Date
              </th>
              <th class="text-left">
                Product Name
              </th>
              <th class="text-left">
                Current Status
              </th>
              <th class="text-left">
                Reference Number
              </th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="item in listings" :key="item.id">
              <td>{{ moment(item.orderTime).format("MMMM Do YYYY") }}</td>
              <td>{{ item.productName }}</td>
              <td>{{ item.status }}</td>
              <td>{{ item.shipmentReference }}</td>
            </tr>
          </tbody>
        </v-table>
    </div>
    <div v-else>
      <h1>You have not placed any order</h1>
    </div>
    </v-container>
  </template>
<script>
import orderService from "@/services/order.service";
export default {
  inject: ["moment"],
  data() {
    return {
      notEmpty: true,
      listings: [],
    };
  },
  mounted() {
    orderService.getOrders().then((response) => {
      const orderedOrders = response.data;
      if (orderedOrders.length > 0) {
        this.listings = orderedOrders;
      } else {
        this.notEmpty = false;
      }
    });
  },
};
</script>

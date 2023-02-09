<!-- eslint-disable prettier/prettier -->
<template>
  <div v-if ="notEmpty">
      <v-table class="ma-15" style="margin: 0 auto; width:65%" :data="listings">
        <thead>
          <tr>
            <th class="text-left" @click="sortListing('name')">
              Product name
            </th>
            <th class="text-left">
              Description
            </th>
            <th class="text-left" @click="sortListing('price')">
              Price
            </th>
            <th class="text-left">
              Condition
            </th>
            <th class="text-left">
              Status
            </th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="item in listings" :key="item.name">
            <td>{{ item.name }}</td>
            <td>{{ item.description }}</td>
            <td>${{ item.price }}</td>
            <td>{{ item.condition }}</td>
            <td>{{ item.status }}</td>
          </tr>
        </tbody>
      </v-table>
  </div>
  <div v-else>
    <h1>You have no listing so far. Would you like to sell a product?</h1>
  </div>

</template>
<script>
export default {
  data() {
    return {
      notEmpty: true,
      listings: [
        {
          name: "Frozen Yogurt",
          description: "YUUUUUMMMMMM",
          price: 99,
          condition: "Like new",
          status: "Listing",
        },
        {
          name: "Ice cream sandwich",
          description: 237,
          price: 99,
          condition: "Like new",
          status: "Listing",
        },
        {
          name: "Eclair",
          description: 262,
          price: 99,
          condition: "Like new",
          status: "Listing",
        },
        {
          name: "Cupcake",
          description: 305,
          price: 99,
          condition: "Like new",
          status: "Listing",
        },
        {
          name: "Gingerbread",
          description: 356,
          price: 99,
          condition: "Like new",
          status: "Listing",
        },
        {
          name: "Jelly bean",
          description: 375,
          price: 99,
          condition: "Like new",
          status: "Listing",
        },
        {
          name: "Lollipop",
          description: 392,
          price: 99,
          condition: "Like new",
          status: "Listing",
        },
        {
          name: "Honeycomb",
          description: 408,
          price: 99,
          condition: "Like new",
          status: "Listing",
        },
        {
          name: "Donut",
          description: 452,
          price: 99,
          condition: "Like new",
          status: "Listing",
        },
        {
          name: "KitKat",
          description: 518,
          price: 99,
          condition: "Like new",
          status: "Listing",
        },
      ],
    };
  },
};
</script>

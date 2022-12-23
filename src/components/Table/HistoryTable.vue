<template>
  <Load v-if="!loaded"/>
  <div class="table-wrapper" v-if="loaded">
    <table>
      <thead>
      <tr>
        <th>Name</th>
        <th>Action</th>
        <th>Sent On</th>
        <th>Received On</th>
        <th>To Person</th>
        <th>Origin</th>
        <th>From Person</th>
        <th>Destination</th>
      </tr>
      </thead>
      <tbody>
      <tr v-for="item in this.tableData">
        <td>{{ item.name }}</td>
        <td :class="[item.action === 'SENT' ? sentClass : receivedClass ]">{{ item.action }}</td>
        <td >{{ item.timeSent }}</td>
        <td>{{ item.timeReceived }}</td>
        <td>{{ item.receiver }}</td>
        <td>{{ item.origin }}</td>
        <td>{{ item.sender }}</td>
        <td>{{ item.destination }}</td>
      </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
import {mapActions, mapGetters} from "vuex";
import {get} from "@/assets/js/data-connector/api-communication-abstractor";
import Load from "@/components/Load/Load.vue";
import {itemsToUserReadable} from "@/assets/js/helper";

export default {
  name: "HistoryTable",
  components: {Load},
  props: {
    items: Array
  },
  data() {
    return {
      sentClass: 'sent',
      receivedClass: 'received',
      tableData: undefined,
      loaded: false
    };
  },
  computed: {
    ...mapGetters(['transporters', 'users'])
  },
  methods: {
    getTableData(items) {
      return itemsToUserReadable(items, this.transporters, this.users);
    }
  },
  async created() {

    if(this.transporters && this.users) {
      this.tableData = this.getTableData(this.items);
      this.loaded = true;
    }

  },
  watch: {
    items(nw, old) {
      this.tableData = this.getTableData(nw);
    }
  }
};
</script>

<style scoped lang="scss">

.table-wrapper {
  margin: 0;
  background: var(--color-background);
  font-family: sans-serif;
  font-weight: 100;
}

table {
  width: 100%;
  border-collapse: collapse;
  overflow: hidden;
  box-shadow: var(--box-shadow);
  border-radius: var(--box-radius);
}

th, td {
  padding: 12px;
  background-color: rgba(255,255,255,0.2);
  color: var(--color-primary-soft);
}

th {
  text-align: left;
}

thead {
  th {
    background-color: var(--color-secondary);
    color: var(--color-white);
  }
}

tbody {
  tr {
    &:hover {
      background-color: rgba(118, 120, 122, 0.1);
    }
  }
  td {
    position: relative;
    &:hover {
      &:before {
        content: "";
        position: absolute;
        left: 0;
        right: 0;
        top: -9999px;
        bottom: -9999px;
        background-color: rgba(255,255,255,0.2);
        z-index: -1;
      }
    }
  }
}

.sent {
  color: var(--color-green);
  text-transform: capitalize;
}

.received {
  color: var(--color-orange);
  text-transform: capitalize;
}

</style>


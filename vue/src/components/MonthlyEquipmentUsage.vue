<template>
  <div class="monthly-equipment-usage">
    <h2 class="title">Monthly Gym Equipment Usage View</h2>
    <form class="usage-form" @submit.prevent="fetchMonthlyEquipmentUsage">
      <div class="form-group">
        <label for="month">Month:</label>
        <input type="number" id="month" v-model="selectedMonth" min="1" max="12" />
      </div>
      <div v-if="loading" class="loading-indicator">
  Loading...
</div>
      <div class="form-group">
        <label for="year">Year:</label>
        <input type="number" id="year" v-model="selectedYear" min="2020" max="2099" />
      </div>
      <button type="submit" class="generate-button">View</button>
    </form>

    <div v-if="monthlyEquipmentUsage.length" class="usage-table">
      <h3>Equipment Usage for {{ selectedMonth }}/{{ selectedYear }}</h3>
      <table>
        <thead>
          <tr>
            <th>Equipment Name</th>
            <th>Usage Count</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="usage in monthlyEquipmentUsage" :key="usage.id">
            <td>{{ usage.equipmentName }}</td>
            <td>{{ usage.usageCount }}</td>
          </tr>
        </tbody>
      </table>
    </div>
    <div v-else class="no-data">
      <p>No equipment usage data available.</p>
    </div>
  <member-usage-chart :chartData="chartData" />
  </div>
</template>

<script>



export default {
  data() {
    return {
      selectedMonth: new Date().getMonth() + 1,
      selectedYear: new Date().getFullYear(),
      monthlyEquipmentUsage: [],
      loading: false,
      gymMembers: [
        { id: 1, name: 'John Doe' },
        { id: 2, name: 'Jane Smith' },
        // Add more gym members here
      ],
      chartData: {}, // Initialize chartData object
    };
  },
  mounted() {
  console.log(this.gymMembers);
  },
  methods: {
    async fetchMonthlyEquipmentUsage() {
      try {
        // Mocked equipment usage data for demonstration
        const mockEquipmentUsage = [
          { equipmentName: 'Treadmill', usageCount: 30 },
          { equipmentName: 'Dumbbell', usageCount: 45 },
          // Add more equipment usage data here
        ];
        this.monthlyEquipmentUsage = mockEquipmentUsage;

        // Generate chart data based on gym members and equipment usage
        this.chartSeries = this.gymMembers.map((member) => ({
      name: member.name,
      data: mockEquipmentUsage.map((usage) => ({
        x: usage.equipmentName,
        y: Math.floor(Math.random() * 50), // Random usage count for demonstration
      })),
        }));
      } catch (error) {
        console.error('Error fetching equipment usage:', error);
      }
    },
  },
};
</script>
<style scoped>
.monthly-equipment-usage {
   border-radius: 50px;
background: linear-gradient(315deg, #cacaca, #f0f0f0);
box-shadow:  -8px -8px 16px #b5b5b5,
             8px 8px 16px #ffffff;
}

.title {
  font-size: 24px;
  margin-bottom: 20px;
}

.usage-form {
  display: flex;
  flex-direction: column;
  gap: 10px;
  margin-bottom: 20px;
}

.form-group {
  display: flex;
  gap: 10px;
  align-items: center;
}

.generate-button {
  background-color: #007bff;
  color: white;
  border: none;
  padding: 10px 20px;
  cursor: pointer;
  border-radius: 40px;
  margin: auto 350px;
}

.usage-table {
  margin-top: 20px;
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  margin-bottom: 20px;
  padding: 10px;
  border: 1px solid #ddd;
  border-radius: 0.5rem;
  background: black;
  background: -webkit-linear-gradient(to right, #3f4c6b, #606c88);
  background: linear-gradient(to right top, #3f4c6b, #606c88);
  padding: 1rem;
  color: white;
  box-shadow: 0px 87px 78px -39px rgba(0, 0, 0, 0.4);
  margin: auto;
  width: 32em;
}


.usage-table table {
  width: 100%;
  border-collapse: collapse;
}

.usage-table th, .usage-table td {
  padding: 10px;
  border: 1px solid #ccc;
  text-align: left;
}

.no-data {
  margin-top: 20px;
  text-align: center;
  font-style: italic;
  color: #888;
}
label{
  margin-left:400px ;
  
}

</style>



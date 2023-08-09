const members = [
    { id: 1, name: "John", checkedIn: false, checkInTime: null },
    { id: 2, name: "Jane", checkedIn: false, checkInTime: null },
  ];
  
  export function getMember(id) {
    return members.find((member) => member.id === id);
  }
  
  export function checkIn(id) {
    const member = getMember(id);
    if (member) {
      member.checkedIn = true;
      member.checkInTime = new Date();
    }
  }
  
  export function checkOut(id) {
    const member = getMember(id);
    if (member) {
      member.checkedIn = false;
      member.checkInTime = null;
    }
  }
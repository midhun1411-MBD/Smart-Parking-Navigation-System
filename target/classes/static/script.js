// API Base URL
const API_BASE = 'http://localhost:8080/api';

// Current User
let currentUser = null;

// Initialize
document.addEventListener('DOMContentLoaded', () => {
    checkLoginStatus();
});

// Tab Management
function showTab(tab) {
    const loginTab = document.getElementById('loginTab');
    const registerTab = document.getElementById('registerTab');
    const tabs = document.querySelectorAll('.tab-btn');
    
    tabs.forEach(btn => btn.classList.remove('active'));
    
    if (tab === 'login') {
        loginTab.classList.add('active');
        registerTab.classList.remove('active');
        tabs[0].classList.add('active');
    } else {
        registerTab.classList.add('active');
        loginTab.classList.remove('active');
        tabs[1].classList.add('active');
    }
}

// Check Login Status
function checkLoginStatus() {
    const user = localStorage.getItem('currentUser');
    if (user) {
        currentUser = JSON.parse(user);
        showDashboard();
    }
}

// Handle Login
async function handleLogin(event) {
    event.preventDefault();
    
    const userId = document.getElementById('loginUserId').value;
    const password = document.getElementById('loginPassword').value;
    
    // Validation
    if (!userId || userId.trim().length === 0) {
        showToast('Please enter a User ID', 'error');
        return;
    }
    
    if (!password || password.length === 0) {
        showToast('Please enter a password', 'error');
        return;
    }
    
    try {
        const response = await fetch(`${API_BASE}/login`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({ userId, password })
        });
        
        if (!response.ok) {
            const errorData = await response.json();
            showToast(errorData.message || 'Login failed', 'error');
            return;
        }
        
        const data = await response.json();
        
        if (data.success) {
            currentUser = data.user;
            localStorage.setItem('currentUser', JSON.stringify(currentUser));
            showToast('Login successful!', 'success');
            showDashboard();
        } else {
            showToast(data.message || 'Login failed', 'error');
        }
    } catch (error) {
        showToast('Cannot connect to server. Please make sure the server is running.', 'error');
        console.error('Login error:', error);
    }
}

// Handle Register
async function handleRegister(event) {
    event.preventDefault();
    
    const userId = document.getElementById('regUserId').value;
    const name = document.getElementById('regName').value;
    const password = document.getElementById('regPassword').value;
    
    // Validation
    if (!userId || userId.trim().length === 0) {
        showToast('Please enter a User ID', 'error');
        return;
    }
    
    if (!name || name.trim().length === 0) {
        showToast('Please enter your name', 'error');
        return;
    }
    
    if (!password || password.length < 3) {
        showToast('Password must be at least 3 characters', 'error');
        return;
    }
    
    try {
        const response = await fetch(`${API_BASE}/register`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({ userId, password, name })
        });
        
        if (!response.ok) {
            const errorData = await response.json();
            showToast(errorData.message || 'Registration failed', 'error');
            return;
        }
        
        const data = await response.json();
        
        if (data.success) {
            showToast('Registration successful! Please login.', 'success');
            showTab('login');
            document.getElementById('registerForm').reset();
            // Pre-fill login form
            document.getElementById('loginUserId').value = userId;
        } else {
            showToast(data.message || 'Registration failed', 'error');
        }
    } catch (error) {
        showToast('Cannot connect to server. Please make sure the server is running.', 'error');
        console.error('Register error:', error);
    }
}

// Logout
function logout() {
    currentUser = null;
    localStorage.removeItem('currentUser');
    document.getElementById('authSection').style.display = 'flex';
    document.getElementById('dashboardSection').style.display = 'none';
    document.getElementById('userInfo').style.display = 'none';
    showToast('Logged out successfully!', 'success');
}

// Show Dashboard
function showDashboard() {
    document.getElementById('authSection').style.display = 'none';
    document.getElementById('dashboardSection').style.display = 'block';
    document.getElementById('userInfo').style.display = 'flex';
    document.getElementById('userName').textContent = `Welcome, ${currentUser.name}`;
    
    refreshSlots();
    loadBookings();
}

// Refresh Slots
async function refreshSlots() {
    try {
        const response = await fetch(`${API_BASE}/slots`);
        const data = await response.json();
        
        if (data.success) {
            updateStats(data.totalSlots, data.availableCount);
            renderSlots(data.slots);
        } else {
            showToast(data.message, 'error');
        }
    } catch (error) {
        showToast('Failed to load slots.', 'error');
        console.error('Refresh slots error:', error);
    }
}

// Update Stats
function updateStats(total, available) {
    document.getElementById('totalSlots').textContent = total;
    document.getElementById('availableSlots').textContent = available;
    document.getElementById('occupiedSlots').textContent = total - available;
}

// Render Slots
function renderSlots(slots) {
    const grid = document.getElementById('slotsGrid');
    grid.innerHTML = '';
    
    slots.forEach(slot => {
        const slotCard = document.createElement('div');
        slotCard.className = `slot-card ${slot.isOccupied ? 'occupied' : 'available'}`;
        slotCard.onclick = () => selectSlot(slot.slotId);
        
        slotCard.innerHTML = `
            <span class="slot-number">${slot.slotId}</span>
            <span class="slot-status">${slot.isOccupied ? 'Occupied' : 'Available'}</span>
            ${slot.vehicleId ? `<span class="slot-vehicle">${slot.vehicleId}</span>` : ''}
        `;
        
        grid.appendChild(slotCard);
    });
}

// Select Slot
function selectSlot(slotId) {
    document.getElementById('slotId').value = slotId;
    showToast(`Slot ${slotId} selected`, 'success');
}

// Find Nearest Slot
async function findNearestSlot() {
    try {
        const response = await fetch(`${API_BASE}/slots/nearest`);
        const data = await response.json();
        
        if (data.success && data.slot) {
            const slotId = data.slot.slotId;
            document.getElementById('slotId').value = slotId;
            showToast(`Nearest available slot: ${slotId}`, 'success');
            
            // Highlight the slot
            const slotCards = document.querySelectorAll('.slot-card');
            slotCards.forEach(card => {
                if (card.textContent.includes(slotId)) {
                    card.style.border = '3px solid #f59e0b';
                    setTimeout(() => {
                        card.style.border = '2px solid transparent';
                    }, 3000);
                }
            });
        } else {
            showToast('No available slots found', 'warning');
        }
    } catch (error) {
        showToast('Failed to find nearest slot.', 'error');
        console.error('Find nearest slot error:', error);
    }
}

// Simulate Detection
async function simulateDetection() {
    try {
        const response = await fetch(`${API_BASE}/slots/simulate`, {
            method: 'POST'
        });
        
        const data = await response.json();
        
        if (data.success) {
            showToast('Slot detection simulated!', 'success');
            refreshSlots();
        } else {
            showToast(data.message, 'error');
        }
    } catch (error) {
        showToast('Failed to simulate detection.', 'error');
        console.error('Simulate detection error:', error);
    }
}

// Handle Booking
async function handleBooking(event) {
    event.preventDefault();
    
    const vehicleId = document.getElementById('vehicleId').value;
    const slotId = parseInt(document.getElementById('slotId').value);
    const paymentAmount = parseFloat(document.getElementById('paymentAmount').value);
    
    try {
        const response = await fetch(`${API_BASE}/bookings`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                userId: currentUser.userId,
                vehicleId,
                slotId,
                paymentAmount
            })
        });
        
        const data = await response.json();
        
        if (data.success) {
            showToast('Slot booked successfully!', 'success');
            document.getElementById('bookingForm').reset();
            refreshSlots();
            loadBookings();
        } else {
            showToast(data.message, 'error');
        }
    } catch (error) {
        showToast('Failed to book slot.', 'error');
        console.error('Booking error:', error);
    }
}

// Handle Exit
async function handleExit(event) {
    event.preventDefault();
    
    const vehicleId = document.getElementById('exitVehicleId').value;
    
    try {
        const response = await fetch(`${API_BASE}/exit`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({ vehicleId })
        });
        
        const data = await response.json();
        
        if (data.success) {
            showToast(`Vehicle exited! Payment: $${data.payment.toFixed(2)}`, 'success');
            document.getElementById('exitForm').reset();
            refreshSlots();
        } else {
            showToast(data.message, 'error');
        }
    } catch (error) {
        showToast('Failed to exit vehicle.', 'error');
        console.error('Exit error:', error);
    }
}

// Load Bookings
async function loadBookings() {
    try {
        const response = await fetch(`${API_BASE}/bookings`);
        const data = await response.json();
        
        if (data.success) {
            renderBookings(data.bookings);
        } else {
            showToast(data.message, 'error');
        }
    } catch (error) {
        showToast('Failed to load bookings.', 'error');
        console.error('Load bookings error:', error);
    }
}

// Render Bookings
function renderBookings(bookings) {
    const list = document.getElementById('bookingsList');
    list.innerHTML = '';
    
    if (bookings.length === 0) {
        list.innerHTML = '<p style="text-align: center; color: #64748b;">No bookings yet</p>';
        return;
    }
    
    bookings.reverse().forEach(booking => {
        const card = document.createElement('div');
        card.className = 'booking-card';
        
        const bookingTime = new Date(booking.bookingTime).toLocaleString();
        
        card.innerHTML = `
            <div class="booking-header">
                <span class="booking-id">${booking.bookingId}</span>
                <span class="booking-amount">$${booking.paymentAmount.toFixed(2)}</span>
            </div>
            <div class="booking-details">
                <div class="booking-detail">
                    <span class="booking-detail-label">User:</span>
                    <span>${booking.userId}</span>
                </div>
                <div class="booking-detail">
                    <span class="booking-detail-label">Vehicle:</span>
                    <span>${booking.vehicleId}</span>
                </div>
                <div class="booking-detail">
                    <span class="booking-detail-label">Slot:</span>
                    <span>${booking.slotId}</span>
                </div>
                <div class="booking-detail">
                    <span class="booking-detail-label">Time:</span>
                    <span>${bookingTime}</span>
                </div>
            </div>
        `;
        
        list.appendChild(card);
    });
}

// Show Toast
function showToast(message, type = 'success') {
    const toast = document.getElementById('toast');
    toast.textContent = message;
    toast.className = `toast ${type} show`;
    
    setTimeout(() => {
        toast.classList.remove('show');
    }, 3000);
}

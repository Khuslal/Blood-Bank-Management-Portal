// ========================================
// LifeLine Dashboard JavaScript
// ========================================

document.addEventListener("DOMContentLoaded", function() {

    initializeClock();
    initializeSidebar();
    initializeCounters();
    initializeChart();
    initializeCardAnimation();
    initializeProgressAnimation();

});

// ========================================
// Nepal Standard Time Clock
// ========================================

function initializeClock() {

    const clock = document.getElementById("clock");

    function updateClock() {

        const options = {
            timeZone: "Asia/Kathmandu",
            weekday: "short",
            day: "2-digit",
            month: "short",
            year: "numeric",
            hour: "2-digit",
            minute: "2-digit",
            second: "2-digit"
        };

        clock.innerHTML =
            '<i class="bi bi-clock-fill text-danger"></i> ' +
            new Intl.DateTimeFormat("en-GB", options).format(new Date());

    }

    updateClock();

    setInterval(updateClock, 1000);

}

// ========================================
// Sidebar Toggle
// ========================================

function initializeSidebar() {

    const menu = document.getElementById("menuBtn");
    const sidebar = document.querySelector(".sidebar");

    if (!menu) return;

    menu.addEventListener("click", () => {

        sidebar.classList.toggle("active");

    });

}

// ========================================
// Animated Counters
// ========================================

function initializeCounters() {

    const numbers = document.querySelectorAll(".card-box h2");

    numbers.forEach(function(item) {

        const target = parseInt(item.innerText);

        if (isNaN(target)) return;

        let current = 0;

        const speed = target / 80;

        const timer = setInterval(function() {

            current += speed;

            if (current >= target) {

                current = target;

                clearInterval(timer);

            }

            item.innerText = Math.floor(current);

        }, 20);

    });

}

// ========================================
// Progress Bar Animation
// ========================================

function initializeProgressAnimation() {

    const progress = document.querySelector(".progress-bar");

    if (!progress) return;

    const width = progress.style.width;

    progress.style.width = "0%";

    setTimeout(() => {

        progress.style.width = width;

    }, 300);

}

// ========================================
// Fade In Cards
// ========================================

function initializeCardAnimation() {

    const cards = document.querySelectorAll(".dashboard-card,.card-box");

    cards.forEach((card, index) => {

        card.style.opacity = 0;

        card.style.transform = "translateY(40px)";

        setTimeout(() => {

            card.style.transition = ".6s ease";

            card.style.opacity = 1;

            card.style.transform = "translateY(0)";

        }, index * 150);

    });

}

// ========================================
// Chart.js
// ========================================

function initializeChart() {

    const canvas = document.getElementById("donationChart");

    if (!canvas) return;

    new Chart(canvas, {

        type: 'line',

        data: {

            labels: [
                "Jan",
                "Feb",
                "Mar",
                "Apr",
                "May",
                "Jun",
                "Jul"
            ],

            datasets: [

                {

                    label: "Donations",

                    data: [2, 4, 3, 6, 5, 8, 7],

                    borderColor: "#dc3545",

                    backgroundColor: "rgba(220,53,69,.15)",

                    fill: true,

                    tension: .4,

                    pointRadius: 5,

                    pointBackgroundColor: "#dc3545"

                }

            ]

        },

        options: {

            responsive: true,

            plugins: {

                legend: {

                    display: false

                }

            },

            scales: {

                y: {

                    beginAtZero: true

                }

            }

        }

    });

}

// ========================================
// Toast Notification
// ========================================

setTimeout(function() {

    const toast = document.createElement("div");

    toast.className = "position-fixed bottom-0 end-0 p-3";

    toast.style.zIndex = "9999";

    toast.innerHTML = `

<div class="toast show shadow">

<div class="toast-header bg-danger text-white">

<strong class="me-auto">

🩸 LifeLine

</strong>

<button class="btn-close btn-close-white"
data-bs-dismiss="toast"></button>

</div>

<div class="toast-body">

Welcome back! Have a wonderful day ❤️

</div>

</div>

`;

    document.body.appendChild(toast);

}, 1500);

// ========================================
// Card Hover Glow
// ========================================

document.querySelectorAll(".card-box").forEach(card => {

    card.addEventListener("mouseenter", () => {

        card.style.boxShadow = "0 15px 40px rgba(220,53,69,.25)";

    });

    card.addEventListener("mouseleave", () => {

        card.style.boxShadow = "";

    });

});

// ========================================
// Emergency Badge Pulse
// ========================================

setInterval(function() {

    document.querySelectorAll(".bg-danger").forEach(item => {

        item.classList.toggle("opacity-75");

    });

}, 700);
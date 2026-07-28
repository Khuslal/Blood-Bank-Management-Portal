// ==========================================================================
// LifeLine Dashboard Unified Scripting Pipeline Engine
// ==========================================================================

document.addEventListener("DOMContentLoaded", function() {

    // Bootstrapping interactive modules sequentially
    initializeClock();
    initializeSidebar();
    initializeCounters();
    initializeChart();
    initializeCardAnimation();
    initializeProgressAnimation();

});

// ==========================================================================
// 1. Integrated Digital Operational Clock (Nepal Standard Time Grid)
// ==========================================================================
function initializeClock() {
    const clock = document.getElementById("clock");
    if (!clock) return;

    function updateClock() {
        try {
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
        } catch (error) {
            // High-fidelity fallback block if hardware engine rejects locale parameters
            const fallbackDate = new Date();
            clock.innerText = fallbackDate.toLocaleTimeString([], { 
                hour: '2-digit', 
                minute: '2-digit', 
                second: '2-digit' 
            });
        }
    }

    updateClock();
    setInterval(updateClock, 1000);
}

// ==========================================================================
// 2. Mobile-Optimized Leak-Proof Sidebar Controller Matrix
// ==========================================================================
function initializeSidebar() {
    const menuBtn = document.getElementById("menuBtn");
    const sidebar = document.getElementById("appSidebar") || document.querySelector(".sidebar");

    if (!menuBtn || !sidebar) return;

    // Toggle menu state cleanly while stopping tracking events from bubbling out
    menuBtn.addEventListener("click", (e) => {
        e.stopPropagation();
        sidebar.classList.toggle("active");
    });

    // Mobile layout optimization pipeline: intercept outside leak clicks and safely retract menu
    document.addEventListener("click", function (e) {
        if (!sidebar.contains(e.target) && !menuBtn.contains(e.target) && sidebar.classList.contains("active")) {
            sidebar.classList.remove("active");
        }
    });
}

// ==========================================================================
// 3. Dynamic Roll-up Statistical Counter System
// ==========================================================================
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

// ==========================================================================
// 4. Progress Loading Layout Automation Loop
// ==========================================================================
function initializeProgressAnimation() {
    const progress = document.querySelector(".progress-bar");
    if (!progress) return;

    const width = progress.style.width;
    progress.style.width = "0%";

    setTimeout(() => {
        progress.style.width = width;
    }, 300);
}

// ==========================================================================
// 5. Visual Entry Frame Interceptor (Fade-in Layout Delay Matrix)
// ==========================================================================
function initializeCardAnimation() {
    const cards = document.querySelectorAll(".dashboard-card, .card-box");

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

// ==========================================================================
// 6. Analytics Line Graphics Chart Configuration Engine
// ==========================================================================
// ==========================================================================
// 6. Analytics Line Graphics Chart Configuration Engine
// ==========================================================================
function initializeChart() {
    const canvas = document.getElementById("donationChart");
    if (!canvas) return;

    // Explicitly declaring the array inside a variable to force text compilation safety
    const donationValues = new Array(2, 4, 3, 6, 5, 8, 7);

    new Chart(canvas, {
        type: 'line',
        data: {
            labels: ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul"],
            datasets: [
                {
                    label: "Donations",
                    data: donationValues,
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

// ==========================================================================
// 7. Welcome Toast Push Delivery Layout Interface
// ==========================================================================
setTimeout(function() {
    const toast = document.createElement("div");
    toast.className = "position-fixed bottom-0 end-0 p-3";
    toast.style.zIndex = "9999";
    toast.innerHTML = `
        <div class="toast show shadow border-0" role="alert" aria-live="assertive" aria-atomic="true">
            <div class="toast-header bg-danger text-white">
                <strong class="me-auto">🩸 LifeLine</strong>
                <button class="btn-close btn-close-white" data-bs-dismiss="toast" aria-label="Close"></button>
            </div>
            <div class="toast-body">
                Welcome back! Have a wonderful day ❤️
            </div>
        </div>
    `;
    document.body.appendChild(toast);
}, 1500);

// ==========================================================================
// 8. Visual Hover Interaction Micro-animations Matrix
// ==========================================================================
document.querySelectorAll(".card-box").forEach(card => {
    card.addEventListener("mouseenter", () => {
        card.style.boxShadow = "0 15px 40px rgba(220,53,69,.25)";
    });
    card.addEventListener("mouseleave", () => {
        card.style.boxShadow = "";
    });
});

// ==========================================================================
// 9. Emergency Component Layout Pulse Dispatcher Loop
// ==========================================================================
setInterval(function() {
    document.querySelectorAll(".bg-danger").forEach(item => {
        item.classList.toggle("opacity-75");
    });
}, 700);

// Mocking the Java classes in JavaScript
class Rating {
    constructor(ratingId, customerId, score, review, ratingDate) {
        this.ratingId = ratingId;
        this.customerId = customerId;
        this.score = score;
        this.review = review;
        this.ratingDate = ratingDate;
    }
}

class Product {
    constructor(productId, productName, category, imageUrl) {
        this.productId = productId;
        this.productName = productName;
        this.category = category;
        this.imageUrl = imageUrl;
        this.ratings = [];
    }

    addRating(rating) {
        this.ratings.push(rating);
    }

    calculateAverageRating() {
        if (this.ratings.length === 0) return 0;
        const sum = this.ratings.reduce((acc, r) => acc + r.score, 0);
        return (sum / this.ratings.length).toFixed(1);
    }

    getRatingCount() {
        return this.ratings.length;
    }
}

class RatingManager {
    constructor() {
        this.products = new Map();
    }

    addProduct(product) {
        this.products.set(product.productId, product);
    }

    getProduct(productId) {
        return this.products.get(productId);
    }

    getAllProducts() {
        return Array.from(this.products.values());
    }
}

// Initializing the Manager
const manager = new RatingManager();

// Seed Data
const productsData = [
    { id: 101, name: "AeroBoost Sneakers", cat: "Footwear", img: "aeroboost_sneakers_1778523743691.png" },
    { id: 102, name: "Titan Gaming Mouse", cat: "Peripherals", img: "titan_gaming_mouse_1778523758943.png" },
    { id: 103, name: "EcoGlow Desk Lamp", cat: "Home Decor", img: "ecoglow_desk_lamp_1778523774470.png" }
];

productsData.forEach(p => {
    manager.addProduct(new Product(p.id, p.name, p.cat, p.img));
});

// Adding Initial Ratings
const seedRatings = [
    { pid: 101, r: new Rating(1, "Alice", 5, "Super comfortable for running!", "2026-05-01") },
    { pid: 101, r: new Rating(2, "Bob", 4, "Great style, fits well.", "2026-05-02") },
    { pid: 102, r: new Rating(3, "Charlie", 5, "Precision is unmatched. Love the RGB!", "2026-05-03") },
    { pid: 102, r: new Rating(4, "David", 5, "Best mouse I've used in years.", "2026-05-04") },
    { pid: 103, r: new Rating(5, "Eve", 3, "Light is good, but the base is a bit wobbly.", "2026-05-05") },
    { pid: 103, r: new Rating(6, "Frank", 4, "Modern look, nice warm light.", "2026-05-06") }
];

seedRatings.forEach(sr => manager.getProduct(sr.pid).addRating(sr.r));

// DOM Manipulation
const productGrid = document.getElementById('product-grid');
const modalOverlay = document.getElementById('modal-overlay');
const modalBody = document.getElementById('modal-body');
const closeModal = document.getElementById('close-modal');

function renderProducts() {
    productGrid.innerHTML = '';
    manager.getAllProducts().forEach(product => {
        const card = document.createElement('div');
        card.className = 'product-card';
        card.innerHTML = `
            <img src="${product.imageUrl}" alt="${product.productName}" class="product-image">
            <div class="product-category">${product.category}</div>
            <div class="product-name">${product.productName}</div>
            <div class="rating-summary">
                <span class="avg-score">★ ${product.calculateAverageRating()}</span>
                <span class="total-ratings">${product.getRatingCount()} reviews</span>
            </div>
            <button class="btn-primary" onclick="showDetails(${product.productId})">View Details</button>
        `;
        productGrid.appendChild(card);
    });
}

function showDetails(productId) {
    const product = manager.getProduct(productId);
    modalBody.innerHTML = `
        <h2 style="margin-bottom: 0.5rem">${product.productName}</h2>
        <p style="color: var(--accent-color); font-weight: 600; margin-bottom: 1.5rem">${product.category}</p>
        
        <div class="rating-summary">
            <span class="avg-score" style="font-size: 1.5rem">★ ${product.calculateAverageRating()}</span>
            <span class="total-ratings">${product.getRatingCount()} verified reviews</span>
        </div>

        <div class="reviews-list">
            <h3>Customer Reviews</h3>
            ${product.ratings.map(r => `
                <div class="review-item">
                    <div class="review-header">
                        <span class="customer-name">${r.customerId}</span>
                        <span class="review-score">${'★'.repeat(r.score)}${'☆'.repeat(5-r.score)}</span>
                    </div>
                    <p class="review-text">${r.review}</p>
                    <div class="review-date">${r.ratingDate}</div>
                </div>
            `).join('')}
        </div>
    `;
    modalOverlay.classList.remove('hidden');
}

closeModal.onclick = () => modalOverlay.classList.add('hidden');
modalOverlay.onclick = (e) => { if(e.target === modalOverlay) modalOverlay.classList.add('hidden'); };

// Initial Render
renderProducts();

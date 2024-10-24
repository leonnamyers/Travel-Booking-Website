<%@ page import="java.util.Random"%>
<%@ page import="com.iotbay.*" %>
<%@ page import="com.iotbay.Model.*" %>
<%@ page import="java.sql.Timestamp" %>
<%@ page import="java.util.ArrayList"%>

<style>
* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
  font-family: "Poppins", sans-serif;
}

.flex-container{
  width:60%;
}

h3 {
  text-align: center;
  margin-top: 40px;
  margin-bottom: 20px;
  font-size: 32px;
  color: #333;
}

.wrapper {
  max-width: 1100px;
  width: 100%;
  position: relative;
  margin: 0 auto;
}

.wrapper i {
  top: 50%;
  height: 50px;
  width: 50px;
  cursor: pointer;
  font-size: 20px;
  position: absolute;
  text-align: center;
  line-height: 50px;
  background: #fff;
  border-radius: 50%;
  box-shadow: 0 3px 6px rgba(0,0,0,0.23);
  transform: translateY(-50%);
  transition: transform 0.1s linear;
}

.wrapper i:active {
  transform: translateY(-50%) scale(0.85);
}

.wrapper i:first-child {
  left: 0;
}

.wrapper i:last-child {
  right: 0;
}


.wrapper .carousel {
  display: grid;
  grid-auto-flow: column;
  grid-auto-columns: calc(33.33% - 10px);
  overflow-x: auto;
  scroll-snap-type: x mandatory;
  gap: 16px;
  border-radius: 8px;
  scroll-behavior: smooth;
  scrollbar-width: none;
}

.carousel::-webkit-scrollbar {
  display: none;
}

.carousel.no-transition {
  scroll-behavior: auto;
}

.carousel.dragging {
  scroll-snap-type: none;
  scroll-behavior: auto;
}

.carousel.dragging .card {
  cursor: grab;
  user-select: none;
}

.carousel :where(.card, .img) {
  display: flex;
  justify-content: center;
  align-items: center;
}

.carousel .card {
  scroll-snap-align: start;
  height: 342px;
  list-style: none;
  background: #fff;
  cursor: pointer;
  padding-bottom: 15px;
  flex-direction: column;
  border-radius: 8px;
}

.carousel .card .img {
  background: #625cdb;
  height: 160px;
  width: 160px;
  border-radius: 10%;
}

.card .img img {
  width: 153px;
  height: 153px;
  border-radius: 10%;
  object-fit: cover;
  border: 4px solid #fff;
}

.carousel .card h2,
.carousel .card h3 {
  font-weight: 500;
  font-size: 20px;
  margin: 30px 0 5px;
}

.carousel .card span,
.carousel .card p {
  color: #6A6D78;
  font-size: 16px;
}


@media screen and (max-width: 900px) {
  .wrapper .carousel {
    grid-auto-columns: calc((100% / 2) - 9px);
  }
}

@media screen and (max-width: 600px) {
  .wrapper .carousel {
    grid-auto-columns: 100%;
  }
}

.hero-section {
  background: linear-gradient(to left top, #4741ba, #a7abe0);
  color: white;
  padding: 40px;
  text-align: center;
}

.hero-section .hero-content h1 {
  font-size: 40px;
}

.hero-section .hero-content p {
  font-size: 20px;
}

.special-offers {
  padding: 50px;
  background-color: #f4f4f4;
  text-align: center;
}

.special-offers h2 {
  margin-bottom: 20px;
  font-size: 32px;
}

.special-offers ul {
  list-style: none;
  padding: 0;
}

.special-offers ul li {
  margin-bottom: 10px;
  font-size: 20px;
}

.call-to-action {
  padding: 50px;
  background-color: #333;
  color: white;
  text-align: center;
}

.call-to-action h2 {
  font-size: 32px;
  margin-bottom: 10px;
}

.call-to-action p {
  font-size: 20px;
}
</style>

<script>
  window.onload = function() {
    // Destination Carousel
    const leftButton1 = document.getElementById('left1');
    const rightButton1 = document.getElementById('right1');
    const carousel1 = document.getElementById('carousel1');

    leftButton1.addEventListener('click', () => {
      carousel1.scrollBy({
        left: -carousel1.querySelector('.card').clientWidth,
        behavior: 'smooth'
      });
    });

    rightButton1.addEventListener('click', () => {
      carousel1.scrollBy({
        left: carousel1.querySelector('.card').clientWidth,
        behavior: 'smooth'
      });
    });

    // Hotel Carousel
    const leftButton2 = document.getElementById('left2');
    const rightButton2 = document.getElementById('right2');
    const carousel2 = document.getElementById('carousel2');

    leftButton2.addEventListener('click', () => {
      carousel2.scrollBy({
        left: -carousel2.querySelector('.card').clientWidth,
        behavior: 'smooth'
      });
    });

    rightButton2.addEventListener('click', () => {
      carousel2.scrollBy({
        left: carousel2.querySelector('.card').clientWidth,
        behavior: 'smooth'
      });
    });
  };
</script>





<div class="hero-section">
    <div class="hero-content">
        <h1>Explore the World with Dream Escape!</h1>
        <p>Get started with the best deals right now!</p>
    </div>
</div>

<!-- Destinations Carousel -->
<div class="wrapper">
  <h3>Top Destinations</h3>
    <i id="left1" class="fa-solid fa-angle-left"></i>
    <ul id="carousel1" class="carousel">
      <li class="card">
        <div class="img"><img src="images/flightPhoto.jpeg" alt="Paris"></div>
        <h3>Paris, France</h3>
        <p>20% off flights and hotels</p>
      </li>
      <li class="card">
        <div class="img"><img src="images/flightPhoto.jpeg" alt="Sydney"></div>
        <h3>Sydney, Australia</h3>
        <p>Free 2-night hotel stay</p>
      </li>
      <li class="card">
        <div class="img"><img src="images/flightPhoto.jpeg" alt="New York"></div>
        <h3>New York, USA</h3>
        <p>Discounts on city tours and cruises</p>
      </li>
      <li class="card">
        <div class="img"><img src="images/flightPhoto.jpeg" alt="Tokyo"></div>
        <h3>Tokyo, Japan</h3>
        <p>Free airport transfer</p>
      </li>
      <li class="card">
        <div class="img"><img src="images/flightPhoto.jpeg" alt="London"></div>
        <h3>London, UK</h3>
        <p>15% off on city tours</p>
      </li>
    </ul>
    <i id="right1" class="fa-solid fa-angle-right"></i>
</div>

<!-- Hotels Carousel -->
<div class="wrapper">
  <h3>Top Hotels</h3>
    <i id="left2" class="fa-solid fa-angle-left"></i>
    <ul id="carousel2" class="carousel">
      <li class="card">
        <div class="img"><img src="images/Hotel.jpg" alt="Luxury Parisian Hotel"></div>
        <h3>Luxury Parisian Hotel</h3>
        <p>Rate: $200/night</p>
      </li>
      <li class="card">
        <div class="img"><img src="images/Hotel.jpg" alt="Sydney Beach Resort"></div>
        <h3>Sydney Beach Resort</h3>
        <p>Rate: $150/night</p>
      </li>
      <li class="card">
        <div class="img"><img src="images/Hotel.jpg" alt="New York Penthouse"></div>
        <h3>New York Penthouse</h3>
        <p>Rate: $350/night</p>
      </li>
      <li class="card">
        <div class="img"><img src="images/Hotel.jpg" alt="Tokyo Imperial Hotel"></div>
        <h3>Tokyo Imperial Hotel</h3>
        <p>Rate: $180/night</p>
      </li>
      <li class="card">
        <div class="img"><img src="images/Hotel.jpg" alt="London Riverside Inn"></div>
        <h3>London Riverside Inn</h3>
        <p>Rate: $250/night</p>
      </li>
    </ul>

    <i id="right2" class="fa-solid fa-angle-right"></i>
</div>

<!-- Special Offers Section -->
<section class="special-offers">
  <h2>Exclusive Deals</h2>
  <ul>
    <li>Up to 50% off international flights.</li>
    <li>Book a Caribbean cruise and get free excursions.</li>
    <li>Get free hotel upgrades at select hotels.</li>
  </ul>
</section>

<!-- Call to Action -->
<section class="call-to-action">
  <h2>Ready to Start Your Adventure?</h2>
  <p>Check out our best deals and start planning your dream vacation today. No redirects needed!</p>
  <p><strong>Over 50 exclusive deals available right here on Dream Escape!</strong></p>
</section>

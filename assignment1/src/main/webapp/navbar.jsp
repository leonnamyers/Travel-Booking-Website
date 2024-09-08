<div class="navbar">
    <div class="logo">
        Dream Escape
    </div>
    <ul>
        <li><a href="javascript:void(0);" onclick="loadPackagePage()">Package Booking</a></li>
        <li><a href="index.jsp?page=cruise">Cruise</a></li>
        <li><a href="//localhost:8080/flights.jsp">Flight</a></li>
    </ul>
</div>

<script>
function loadPackagePage() {
    window.location.href = 'PackageController?action=loadPackages';
}
</script>

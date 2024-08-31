<div class="navbar">
    <div class="logo">
        Your Travel Booking Website
    </div>
    <ul>
        <li><a href="javascript:void(0);" onclick="loadPackagePage()">Package Booking</a></li>
        <li><a href="index.jsp?page=cruise">Cruise</a></li>
    </ul>
</div>

<script>
function loadPackagePage() {
    window.location.href = 'PackageController?action=loadPackages';
}
</script>

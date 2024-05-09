<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!--   Core JS Files   -->
<script src="${classpath}/backend/assets/js/core/jquery.min.js"></script>
<script src="${classpath}/backend/assets/js/core/popper.min.js"></script>
<script src="${classpath}/backend/assets/js/core/bootstrap.min.js"></script>
<script src="${classpath}/backend/assets/js/plugins/perfect-scrollbar.jquery.min.js"></script>
<!-- Chart JS -->
<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
<script src="${classpath}/backend/assets/js/plugins/chartjs.min.js"></script>
<!--  Notifications Plugin  -->
<script src="${classpath}/backend/assets/js/plugins/bootstrap-notify.js"></script>
<!-- Control Center for Black Dashboard: parallax effects, scripts for the example pages etc -->
<script src="${classpath}/backend/assets/js/black-dashboard.min.js?v=1.0.0"></script>
<!-- Pagination -->
<script src="${classpath}/backend/assets/js/jquery.simplePagination.js"></script>
<!-- Feather -->
<script src="${classpath}/backend/assets/js/feather.min.js"></script>

<script src="https://cdn.trackjs.com/agent/v3/latest/t.js"></script>

<script>
	feather.replace();
</script>

<script>
	window.TrackJS && TrackJS.install({
		token : "ee6fab19c5a04ac1a32a645abde4613a",
		application : "black-dashboard-free"
	});
</script>
<script>
    document.addEventListener("DOMContentLoaded", function () {
        var currentUrl = window.location.href;

        var menuLinks = document.querySelectorAll('.menu a');

        menuLinks.forEach(function (link) {
            if (currentUrl.includes(linkUrl)) {
                link.classList.add('active');
            }
        });
    });
</script>
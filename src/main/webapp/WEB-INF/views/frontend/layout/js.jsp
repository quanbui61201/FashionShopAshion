<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<script src="${classpath }/frontend/js/jquery-3.3.1.min.js"></script>
<script src="${classpath }/frontend/js/bootstrap.min.js"></script>
<script src="${classpath }/frontend/js/jquery.magnific-popup.min.js"></script>
<script src="${classpath }/frontend/js/jquery-ui.min.js"></script>
<script src="${classpath }/frontend/js/mixitup.min.js"></script>
<script src="${classpath }/frontend/js/jquery.countdown.min.js"></script>
<script src="${classpath }/frontend/js/jquery.slicknav.js"></script>
<script src="${classpath }/frontend/js/owl.carousel.min.js"></script>
<script src="${classpath }/frontend/js/jquery.nicescroll.min.js"></script>
<script src="${classpath }/frontend/js/jquery.simplePagination.js"></script>
<script src="${classpath }/frontend/js/main.js"></script>

<script>
    document.addEventListener("DOMContentLoaded", function() {
        var currentUrl = window.location.pathname;

        var menuLinks = document.querySelectorAll(".header__menu ul li a");

        menuLinks.forEach(function(link) {
            var linkUrl = link.getAttribute("href");

            if (currentUrl.includes(linkUrl)) {
                link.parentElement.classList.add("active");
            }
        });
    });
</script>
document.addEventListener('DOMContentLoaded', function () {

	// 1. Sticky navbar gets a shadow once the page is scrolled
	var nav = document.querySelector('.navbar-custom');
	if (nav) {
		window.addEventListener('scroll', function () {
			nav.classList.toggle('shadow-on-scroll', window.scrollY > 8);
		}, { passive: true });
	}

	// 2. Fade edges on the horizontal-scroll nav (mobile/tablet menu)
	document.querySelectorAll('.nav-scroll-wrap').forEach(function (wrap) {
		var scroller = wrap.querySelector('.nav-scroll');
		if (!scroller) return;

		function updateNavEdges() {
			var atStart = scroller.scrollLeft <= 4;
			var atEnd = scroller.scrollLeft + scroller.clientWidth >= scroller.scrollWidth - 4;
			wrap.classList.toggle('is-scrollable-start', !atStart);
			wrap.classList.toggle('is-scrollable-end', !atEnd);
		}

		scroller.addEventListener('scroll', updateNavEdges, { passive: true });
		window.addEventListener('resize', updateNavEdges);
		updateNavEdges();
	});

	// 3. Fade edges on horizontal-scroll tables (donor lists, stock lists, etc.)
	document.querySelectorAll('.table-custom-wrap').forEach(function (wrap) {
		var scroller = wrap.querySelector('.table-scroll');
		if (!scroller) return;

		function updateTableEdges() {
			var atStart = scroller.scrollLeft <= 4;
			var atEnd = scroller.scrollLeft + scroller.clientWidth >= scroller.scrollWidth - 4;
			wrap.classList.toggle('is-scrollable-start', !atStart);
			wrap.classList.toggle('is-scrollable-end', !atEnd);
		}

		scroller.addEventListener('scroll', updateTableEdges, { passive: true });
		window.addEventListener('resize', updateTableEdges);
		updateTableEdges();
	});

});
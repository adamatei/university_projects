const data = [

{name : 'Simon', score : 80},
{name : 'Mary', score : 90},
{name : 'John', score : 60}

];

const width = 800;
const height = 400;
const margin = {top: 50, bottom: 50, left: 50, right: 50}

const svg = d3.select('d3-container')
.append('svg')
.attr('height', height - margin.top - margin.bottom)
.attr('width', width - margin.left - margin.right)
.attr('viewBox', [0, 0, width, height]);

const x = d3.scaleBand()
.domain(d3.range(data.length))
.range([margin.left, width - margin.right])
.padding(0.1)

const y = d3.scaleLinear()
.domain([0, 100])
.range([height - margin.bottom, margin.top]);


svg
.append('g')
.attr('fill', 'royalblue')
.selectAll('rect')
.data(data.sort(a, b) => d3.descending(a.score, b.score))
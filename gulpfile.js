// Include Gulp
var gulp = require('gulp');

// Include plugins
var plugins = require("gulp-load-plugins")({
    pattern: ['gulp-*', 'gulp.*', 'main-bower-files'],
    replaceString: /\bgulp[\-.]/
});

var config = {
    'docs': 'docs/',
    'dest': 'src/main/resources/public/',
    'source': 'bower_components'
};

gulp.task('fonts', function() {
    gulp.src(['bower_components/materialize/dist/fonts/**'])
        .pipe(gulp.dest(config.docs + 'fonts'))
        .pipe(gulp.dest(config.dest + 'fonts'));
});

gulp.task('js', function() {
    gulp.src(plugins.mainBowerFiles())
        .pipe(plugins.filter('*.js'))
        .pipe(gulp.dest(config.docs + 'js'))
        .pipe(gulp.dest(config.dest + 'js'));
});

gulp.task('css', function() {
    gulp.src(plugins.mainBowerFiles())
        .pipe(plugins.filter('*.css'))
        .pipe(gulp.dest(config.docs + 'css'))
        .pipe(gulp.dest(config.dest + 'css'));
});

gulp.task('img', function() {
});

gulp.task('default', ['fonts', 'js', 'css', 'img']);



var TagModel = Backbone.Model.extend({
	urlRoot: 'http://localhost:8180/BookmarkApp/tag',
	url: function() {
		return this.urlRoot;
	}
});
model = new TagModel();
model.fetch();
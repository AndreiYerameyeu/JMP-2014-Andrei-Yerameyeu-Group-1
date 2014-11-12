var TagModel = Backbone.Model.extend({
	urlRoot: 'http://localhost:8080/BookmarkApp/tag',
	url: function() {
		return this.urlRoot;
	}
});
model = new TagModel();
model.fetch();
//model.set('tag', 'HAHAHA');
model.save();
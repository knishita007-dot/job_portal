const mongoose = require('mongoose');

const descriptionSchema = new mongoose.Schema({
  jobId: { type: Number, required: true },
  description: String,
  requirements: [String],
  benefits: [String],
  title: String,
  skills: [String],
  location: String,
  jobType: String,
  company: String,
  embeddings: [Number] // Placeholder if you add embeddings later
}, { timestamps: true });

module.exports = mongoose.model('Description', descriptionSchema);

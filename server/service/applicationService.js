const Application = require('../model/Application');

exports.getAllApplications = async () => await Application.find();
exports.getApplicationById = async (id) => await Application.findById(id);
exports.getApplicationsByUser = async (userId) => await Application.find({ userId });
exports.getApplicationsByJob = async (jobId) => await Application.find({ jobId });
exports.createApplication = async (data) => await Application.create(data);
exports.updateApplicationStatus = async (id, status) => await Application.findByIdAndUpdate(id, { status }, { new: true });
exports.deleteApplication = async (id) => await Application.findByIdAndDelete(id);

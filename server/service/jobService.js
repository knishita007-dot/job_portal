const Job = require('../model/Job');

exports.getAllJobs = async () => await Job.find();
exports.getJobById = async (id) => await Job.findById(id);
exports.createJob = async (data) => await Job.create(data);
exports.updateJob = async (id, data) => await Job.findByIdAndUpdate(id, data, { new: true });
exports.deleteJob = async (id) => await Job.findByIdAndDelete(id);
exports.getJobsByType = async (type) => await Job.find({ type });
exports.getJobsByLocation = async (location) => await Job.find({ location });
exports.getJobsByUser = async (userId) => await Job.find({ userId });
exports.searchJobs = async (keyword) => await Job.find({ title: { $regex: keyword, $options: 'i' } });
